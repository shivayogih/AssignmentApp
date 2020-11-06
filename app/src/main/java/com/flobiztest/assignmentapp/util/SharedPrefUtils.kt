package com.flobiztest.assignmentapp.util

import android.content.Context
import android.content.SharedPreferences
import com.flobiztest.assignmentapp.BuildConfig

import com.google.gson.Gson


class SharedPrefUtils {
    internal var context: Context? = null

    companion object {

        var PREF_NAME = BuildConfig.APPLICATION_ID

        fun clearSharePref(context: Context?) {
            val preferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
        }

        fun getSharedPref(context: Context?): SharedPreferences? {
            return if (context != null) {
                if (context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) != null) {
                    context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                } else {
                    context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                }
            } else {
                null
            }
        }

        fun putData(context: Context?, key: String, value: String) {
            if (context != null) {
                if (getSharedPref(context) != null) {
                    getSharedPref(context)!!.edit().putString(key, value).apply()

                }
            }
        }



        fun putData(context: Context?, key: String, value: Int) {
            if (context != null) {
                if (getSharedPref(context) != null) {
                    getSharedPref(context)!!.edit().putInt(key, value).apply()

                }
            }
        }

        fun putData(context: Context?, key: String, value: Boolean) {
            if (context != null) {
                if (getSharedPref(context) != null) {
                    getSharedPref(context)!!.edit().putBoolean(key, value).apply()

                }
            }
        }

        fun getString(context: Context?, key: String, defaultValue: String?): String? {
            return if (context != null) {
                if (getSharedPref(context) != null) {
                    getSharedPref(context)!!.getString(key, defaultValue)
                } else {
                    null
                }
            } else {
                null
            }
        }

        fun getBoolean(context: Context?, key: String, defaultValue: Boolean): Boolean {
            return if (context != null) {
                if (getSharedPref(context) != null) {
                    getSharedPref(context)!!.getBoolean(key, defaultValue)
                } else {
                    false
                }
            } else {
                false
            }
        }

        fun getInt(context: Context?, key: String, defaultValue: Int): Int {
            return if (context != null) {
                if (getSharedPref(context) != null) {
                    getSharedPref(context)!!.getInt(key, defaultValue)
                } else {
                    0
                }
            } else {
                0
            }
        }


    }
}
