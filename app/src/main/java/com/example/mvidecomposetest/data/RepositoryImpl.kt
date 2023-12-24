package com.example.mvidecomposetest.data

import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

object RepositoryImpl : Repository {

    private val _contactList = mutableMapOf(
        0 to Contact(id = 0, username = "Ivanov Ivan", phone = "+7-999-999-99"),
        1 to Contact(id = 1, username = "Petrov Petr", phone = "+7-999-999-99"),
        2 to Contact(id = 2, username = "Sergeev Sergey", phone = "+7-999-999-99")
    )
    private val contactList: List<Contact>
        get() = _contactList.values.toList()

    private val contactListChangeEvents = MutableSharedFlow<Unit>(replay = 1).apply {
        tryEmit(Unit)
    }

    override val contacts: Flow<List<Contact>> = flow {
        contactListChangeEvents.collect {
            emit(contactList)
        }
    }

    override fun saveContact(contact: Contact) {
        val id = if (contact.id < 0) contactList.size else contact.id
        _contactList[id] = contact.copy(id = id)
        contactListChangeEvents.tryEmit(Unit)
    }
}
