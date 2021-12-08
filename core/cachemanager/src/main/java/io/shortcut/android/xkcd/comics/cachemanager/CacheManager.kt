package io.shortcut.android.xkcd.comics.cachemanager

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import io.shortcut.android.xkcd.comics.base.extension.GsonUtils.toObjectByGson
import kotlin.jvm.internal.ClassReference

class CacheManager(private val context: Context) {

    companion object {
        const val DEVICE_ID = "deviceId"
        const val DEVICE_SIGN_STATE = "isSigned"
    }

    private val preferences: String? = null

    @Volatile
    private var sharedPreferences: SharedPreferences? = null

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return sharedPreferences ?: synchronized(this) {
            context.getSharedPreferences(preferences, Activity.MODE_PRIVATE)
                .also { sharedPreferences = it }
        }
    }

    fun put(key: String, value: String) {
        getSharedPreferences(context).edit().putString(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply()
    }

    fun put(key: String, value: Float) {
        getSharedPreferences(context).edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Int) {
        getSharedPreferences(context).edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Long) {
        getSharedPreferences(context).edit().putLong(key, value).apply()
    }

    fun get(key: String, defaultValue: String): String {
        if (!has(key)) {
            remove(key)
            return defaultValue
        }
        return getSharedPreferences(context).getString(key, defaultValue) ?: defaultValue
    }

    fun get(key: String, defaultValue: Boolean): Boolean {
        if (!has(key)) {
            remove(key)
            return defaultValue
        }
        return getSharedPreferences(context).getBoolean(key, defaultValue)
    }

    fun get(key: String, defaultValue: Float): Float {
        if (!has(key)) {
            remove(key)
            return defaultValue
        }
        return getSharedPreferences(context).getFloat(key, defaultValue)
    }

    fun get(key: String, defaultValue: Int): Int {
        if (!has(key)) {
            remove(key)
            return defaultValue
        }
        return getSharedPreferences(context).getInt(key, defaultValue)
    }

    fun get(key: String, defaultValue: Long): Long {
        if (!has(key)) {
            remove(key)
            return defaultValue
        }
        return getSharedPreferences(context).getLong(key, defaultValue)
    }

    fun get(key: String, defaultValue: String, type: Any): Any? {
        if (!has(key)) {
            remove(key)
            return defaultValue
        }
        val json = getSharedPreferences(context).getString(key, defaultValue)
        return json?.toObjectByGson(ClassReference::class.java)
    }

    fun remove(key: String) {
        getSharedPreferences(context).edit().remove(key).apply()
    }

    fun clear() {
        getSharedPreferences(context).edit().clear().apply()
    }

    fun has(key: String): Boolean {
        return getSharedPreferences(context).contains(key)
    }

    fun registerChangeListener(changeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getSharedPreferences(context).registerOnSharedPreferenceChangeListener(changeListener)
    }

    fun unRegisterChangeListener(changeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getSharedPreferences(context).unregisterOnSharedPreferenceChangeListener(changeListener)
    }

}