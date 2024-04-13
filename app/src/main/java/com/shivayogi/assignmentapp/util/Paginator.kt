package com.shivayogi.assignmentapp.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}