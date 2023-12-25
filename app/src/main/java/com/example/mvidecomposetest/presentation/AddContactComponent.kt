package com.example.mvidecomposetest.presentation

import android.os.Parcelable
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

interface AddContactComponent {

    val model: StateFlow<Model>

    fun onUsernameChanged(username: String)

    fun onPhoneChanged(phone: String)

    fun onSaveContactClicked()

    @Parcelize
    data class Model(
        val username: String,
        val phone: String
    ) : Parcelable
}
