package io.shortcut.android.xkcd.comics.test.dataprovider

import com.google.gson.Gson


class DataProviderUtils {

    companion object {

        inline fun <reified T> getModelFromFileResource(filePath: String, clazz: Class<T>): T {
            val json = javaClass.classLoader?.getResourceAsStream(filePath)
                ?.bufferedReader().use { it?.readText().orEmpty() }
            return Gson().fromJson(json, clazz)
        }

    }
}