package com.example.mvidecomposetest.presentation_legacy

import androidx.lifecycle.ViewModel
import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.AddContactUseCase
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase

class ContactDetailViewModel : ViewModel() {

    private val repository = RepositoryImpl

    private val addContactUseCase = AddContactUseCase(repository)
    private val editContactUseCase = EditContactUseCase(repository)

    fun addContact(username: String, phone: String) {
        addContactUseCase(username, phone)
    }

    fun editContact(contact: Contact) {
        editContactUseCase(contact)
    }
}
