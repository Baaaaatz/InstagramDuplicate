package com.batzalcancia.core.enums

sealed class UiState {
    object Complete : UiState()
    object Loading : UiState()
    object Empty : UiState()
    class Error(val throwable: Throwable) : UiState()
}