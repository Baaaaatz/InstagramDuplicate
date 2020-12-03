package com.batzalcancia.dashboard.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.batzalcancia.core.enums.UiState
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import com.batzalcancia.core.domain.usecase.GetAllPictures
import com.batzalcancia.dashboard.data.paging.InstagramPicturesPager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModel @ViewModelInject constructor(
        private val getAllPictures: GetAllPictures
) : ViewModel() {

    val picturesState = MutableStateFlow<UiState>(UiState.Loading)
    val picture = MutableStateFlow<List<InstagramPicture>>(emptyList())

    private val config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 30
    )

    val pagingData = Pager(
            config = config,
            initialKey = 0
    ) {
        getAllPictures()
    }.flow.cachedIn(viewModelScope)

//    fun executeGetPictures() {
//        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
//            picturesState.value = UiState.Error(throwable)
//        }) {
//            picturesState.value = UiState.Loading
//            val pictures = getAllPictures()
//            picture.value = pictures
//            picturesState.value = UiState.Complete
//            if (pictures.isEmpty()) picturesState.value = UiState.Empty
//        }
//    }

}