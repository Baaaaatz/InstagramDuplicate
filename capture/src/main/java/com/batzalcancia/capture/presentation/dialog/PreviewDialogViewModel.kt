package com.batzalcancia.capture.presentation.dialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import com.batzalcancia.core.domain.usecase.InsertPicture
import com.batzalcancia.core.enums.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PreviewDialogViewModel @ViewModelInject constructor(
        private val insertPicture: InsertPicture
) : ViewModel() {

    val insertPictureState = MutableStateFlow<UiState>(UiState.Loading)

    fun executeSavePicture(picture: InstagramPicture) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            insertPictureState.value = UiState.Error(throwable)
        }) {
            insertPictureState.value = UiState.Loading
            insertPicture(picture)
            insertPictureState.value = UiState.Complete
        }
    }

}