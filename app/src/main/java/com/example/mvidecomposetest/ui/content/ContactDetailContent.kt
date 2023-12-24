package com.example.mvidecomposetest.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.presentation_legacy.ContactDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContact(
    contact: Contact? = null,
    onContactSaved: () -> Unit,
) {
    val viewModel: ContactDetailViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var username by remember {
            mutableStateOf(contact?.username ?: "")
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            placeholder = {
                Text(text = "Username:")
            },
            onValueChange = { username = it }
        )
        var phone by remember {
            mutableStateOf(contact?.phone ?: "")
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phone,
            placeholder = {
                Text(text = "Phone:")
            },
            onValueChange = { phone = it }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (contact == null) {
                    viewModel.addContact(username, phone)
                } else {
                    viewModel.editContact(
                        contact.copy(
                            username = username,
                            phone = phone
                        )
                    )
                }
                onContactSaved()
            }
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun EditContact(
    contact: Contact,
    onContactChanged: () -> Unit,
) {
    AddContact(contact, onContactChanged)
}
