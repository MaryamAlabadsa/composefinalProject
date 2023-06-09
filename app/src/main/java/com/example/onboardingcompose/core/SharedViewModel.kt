package com.example.onboardingcompose.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.onboardingcompose.model.response.UserData
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class SharedViewModel @Inject constructor(@ApplicationContext context: Context) : PreferencesTask {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private val gGson = GsonBuilder().serializeNulls().create()
    private val userData = "USER_DATA"
    private val token = "TOKEN"

    override fun setUserProfile(userProfile: UserData) {
        preferences.edit()?.putString(userData, gGson.toJson(userProfile))?.apply()
    }

    override fun getUserProfile(): UserData =
        gGson.fromJson(preferences.getString(userData, "{}"), UserData::class.java)

    fun clearUserData() {
        preferences.edit()?.remove(userData)?.apply()
    }

    override fun setToken(token: String) {
        preferences.edit()?.putString(token, token)?.apply()
    }

    override fun getToken(): String {
        return preferences.getString(token, "") ?: ""
    }


    override fun clearPreferences() {
        preferences.edit()?.clear()?.apply()
    }

    override fun isGustUser(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGuestStatus(isGust: Boolean) {
        TODO("Not yet implemented")
    }
}
