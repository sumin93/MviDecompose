package com.example.mvidecomposetest.presentation_legacy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.GetContactsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ContactListViewModel : ViewModel() {

    private val repository = RepositoryImpl

    private val getContactsUseCase = GetContactsUseCase(repository)

    val contacts = getContactsUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )
}
