package com.batzalcancia.capture.presentation

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Size
import android.view.*
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.batzalcancia.capture.R
import com.batzalcancia.capture.databinding.FragmentCaptureBinding
import com.batzalcancia.capture.presentation.dialog.PreviewDialog
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File


class CaptureFragment : Fragment(R.layout.fragment_capture) {

    private lateinit var viewBinding: FragmentCaptureBinding

    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var flashMode = ImageCapture.FLASH_MODE_OFF

    private var camera: Camera? = null
    private var imageCapture: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null

    private val onPreviewTouchListener = object : View.OnTouchListener {
        private var actionDownTimestamp: Long = 0
        override fun onTouch(view: View, event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_DOWN) {
                actionDownTimestamp = System.currentTimeMillis()
            } else if (event.action == MotionEvent.ACTION_UP && isInLongPressDuration()) {
                view.performClick()
                performFocus(event.x, event.y)
            }
            return true
        }

        private fun isInLongPressDuration(): Boolean =
                System.currentTimeMillis() - actionDownTimestamp < ViewConfiguration.getLongPressTimeout()
    }

    private val orientationEventListener: OrientationEventListener by lazy {
        object : OrientationEventListener(requireContext(), SensorManager.SENSOR_DELAY_NORMAL) {
            override fun onOrientationChanged(orientation: Int) {
                updateImageCaptureRotation(orientation)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentCaptureBinding.bind(view)

        viewBinding.imgCapture.setOnClickListener { captureImage() }
        viewBinding.imgSwitchLens.setOnClickListener { switchCamera() }
        viewBinding.imgFlash.setOnClickListener { changeFlashMode() }
        viewBinding.imgClose.setOnClickListener { findNavController().navigateUp() }
        viewBinding.viewCamera.setOnTouchListener(onPreviewTouchListener)

        requestRuntimePermission()

        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.viewSafeArea) { v, insets ->
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.setMargins(
                    params.leftMargin,
                    params.topMargin + insets.systemWindowInsetTop,
                    params.rightMargin,
                    params.bottomMargin + insets.systemWindowInsetBottom
            )
            v.layoutParams = params
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.ctlIcons) { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(requireContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        setupCameraProvider()
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        findNavController().navigateUp()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                })
                .check()
    }

    private fun setupCameraProvider() {
        ProcessCameraProvider.getInstance(requireContext()).also { provider ->
            provider.addListener({
                cameraProvider = provider.get()
                bindCamera()
            }, ContextCompat.getMainExecutor(requireContext()))
        }
    }

    private fun updateImageCaptureRotation(orientation: Int) {
        val rotation: Int = when (orientation) {
            in 45..134 -> Surface.ROTATION_270
            in 135..224 -> Surface.ROTATION_180
            in 225..314 -> Surface.ROTATION_90
            else -> Surface.ROTATION_0
        }

        imageCapture?.targetRotation = rotation
    }

    private fun unbindCamera() {
        cameraProvider?.unbindAll()
    }

    private fun createPreview(): Preview = Preview.Builder()
            .build()

    private fun createCameraCapture(): ImageCapture = ImageCapture.Builder()
            .setFlashMode(flashMode)
            .setTargetResolution(Size(720, 720))
            .build()

    private fun createCameraSelector(): CameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

    private fun switchCamera() {
        lensFacing = when (lensFacing) {
            CameraSelector.LENS_FACING_BACK -> CameraSelector.LENS_FACING_FRONT
            else -> CameraSelector.LENS_FACING_BACK
        }
        unbindCamera()
        bindCamera()
    }

    private fun bindCamera() {
        val preview: Preview = createPreview()
        val cameraSelector = createCameraSelector()
        imageCapture = createCameraCapture()
        orientationEventListener.enable()
        camera = cameraProvider?.bindToLifecycle(viewLifecycleOwner, cameraSelector, preview, imageCapture)
        camera?.let { camera ->
            preview.setSurfaceProvider(viewBinding.viewCamera.surfaceProvider)
            viewBinding.imgFlash.isVisible = camera.cameraInfo.hasFlashUnit()
            updateFlashModeButton()
        }
    }

    private fun changeFlashMode() {
        if (camera?.cameraInfo?.hasFlashUnit() == true) {
            flashMode = when (flashMode) {
                ImageCapture.FLASH_MODE_OFF -> ImageCapture.FLASH_MODE_ON
                ImageCapture.FLASH_MODE_ON -> ImageCapture.FLASH_MODE_AUTO
                else -> ImageCapture.FLASH_MODE_OFF
            }
            imageCapture?.flashMode = flashMode
            updateFlashModeButton()
        }
    }

    private fun captureImage() {
        val file = File(requireContext().filesDir.absoluteFile, "temp.jpg")
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        imageCapture?.takePicture(
                outputFileOptions,
                ContextCompat.getMainExecutor(requireContext()),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        PreviewDialog(BitmapFactory.decodeFile(file.absolutePath).rotate()) {
                            it?.dismiss()
                            findNavController().navigateUp()
                        }.show(childFragmentManager, "")
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Snackbar.make(requireView(), "Something went wrong.", Snackbar.LENGTH_SHORT).show()

                    }
                }
        )
    }

    private fun performFocus(x: Float, y: Float) {
        camera?.let { camera ->
            val pointFactory: MeteringPointFactory = viewBinding.viewCamera.meteringPointFactory
            val afPointWidth = 1.0f / 6.0f
            val aePointWidth = afPointWidth * 1.5f
            val afPoint = pointFactory.createPoint(x, y, afPointWidth)
            val aePoint = pointFactory.createPoint(x, y, aePointWidth)
            val future = camera.cameraControl.startFocusAndMetering(
                    FocusMeteringAction.Builder(
                            afPoint,
                            FocusMeteringAction.FLAG_AF
                    ).addPoint(
                            aePoint,
                            FocusMeteringAction.FLAG_AE
                    ).build()
            )
            future.addListener({}, ContextCompat.getMainExecutor(requireContext()))
        }
    }

    private fun updateFlashModeButton() {
        val drawable = ContextCompat.getDrawable(
                requireContext(),
                when (flashMode) {
                    ImageCapture.FLASH_MODE_ON -> R.drawable.ic_flash_on
                    ImageCapture.FLASH_MODE_AUTO -> R.drawable.ic_flash_auto
                    else -> R.drawable.ic_flash_off
                }
        )
        viewBinding.imgFlash.load(drawable)
    }

    override fun onDestroy() {
        super.onDestroy()
        orientationEventListener.disable()
    }

    fun Bitmap.rotate(): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(90f)
        val scaledBitmap = Bitmap.createScaledBitmap(this, width, height, true)
        return Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.width, scaledBitmap.height, matrix, true)
    }

}