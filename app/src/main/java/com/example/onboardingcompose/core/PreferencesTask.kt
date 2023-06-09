package com.example.onboardingcompose.core

import com.example.onboardingcompose.model.response.UserData


interface PreferencesTask {
    fun setUserProfile(userProfile: UserData)
    fun getUserProfile(): UserData


    fun clearPreferences()

    fun isGustUser(): Boolean
    fun setGuestStatus(isGust: Boolean)

    fun setToken(token: String)
    fun getToken(): String

}