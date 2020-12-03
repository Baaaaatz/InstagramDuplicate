package com.batzalcancia.capture.presentation.dialog

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.batzalcancia.capture.R
import com.batzalcancia.capture.databinding.DialogPreviewBinding
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import com.batzalcancia.core.enums.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class PreviewDialog(
        private val bitmap: Bitmap,
        private val onUploadClicked: (Dialog?) -> Unit
) : DialogFragment() {

    private lateinit var viewBinding: DialogPreviewBinding
    private val viewModel: PreviewDialogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)
        isCancelable = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_preview, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DialogPreviewBinding.bind(view)

        viewBinding.btnGoBack.setOnClickListener {
            dismiss()
        }

        viewBinding.btnUpload.setOnClickListener {
            viewModel.executeSavePicture(InstagramPicture(
                    caption = viewBinding.edtDesc.text.toString().trim(),
                    picture = bitmap.toByteArray(),
                    createdAt = System.currentTimeMillis()
            ))
        }

        viewBinding.imgPreview.load(bitmap)

        viewModel.insertPictureState.onEach {
            if (it == UiState.Complete) {
                onUploadClicked(dialog)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    private fun Bitmap.toByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        return stream.toByteArray()
    }


}