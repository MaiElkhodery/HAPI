package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataPreference: UserDataPreference
) {

    suspend fun getUser(): User {
        return User(
            name = userDataPreference.getUsername()!!,
            crop = userDataPreference.getCrop()!!,
            landId = userDataPreference.getLandId()!!,
            role = userDataPreference.getRole()!!
        )
    }
}