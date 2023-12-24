package com.example.mvidecomposetest.domain

class EditContactUseCase(
    private val repository: Repository
) {

    operator fun invoke(
        contact: Contact
    ) {
        repository.saveContact(contact)
    }
}
