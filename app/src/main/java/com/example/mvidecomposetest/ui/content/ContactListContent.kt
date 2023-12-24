package com.example.mvidecomposetest.ui.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.presentation_legacy.ContactListViewModel

@Composable
fun Contacts(
    onAddContactClick: () -> Unit,
    onContactClick: (Contact) -> Unit
) {
    val viewModel: ContactListViewModel = viewModel()
    val contacts by viewModel.contacts.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = contacts,
                key = {
                    it.id
                }
            ) {
                Contact(
                    modifier = Modifier.clickable {
                        onContactClick(it)
                    },
                    username = it.username,
                    phone = it.phone
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.tertiary,
            onClick = {
                onAddContactClick()
            }
        ) {
            Image(
                painter = rememberVectorPainter(image = Icons.Filled.Add),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Contact(
    modifier: Modifier,
    username: String,
    phone: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = username,
            fontSize = 24.sp,
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = phone,
            fontSize = 16.sp
        )
    }
}
