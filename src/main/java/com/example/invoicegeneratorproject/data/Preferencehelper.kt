package com.example.invoicegeneratorproject.data

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREFS_NAME = "invoice_app_prefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_USERNAME = "username"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserId(context: Context, userId: Long) {
        val editor = getPreferences(context).edit()
        editor.putLong(KEY_USER_ID, userId)
        editor.apply()
    }

    public fun getUserId(context: Context): Long {
        return getPreferences(context).getLong(KEY_USER_ID, -1)
    }

    fun saveUsername(context: Context, username: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    fun getUsername(context: Context): String? {
        return getPreferences(context).getString(KEY_USERNAME, null)
    }

    fun clearUserData(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
