package com.skybox.seven.covid.helpers

data class DataSource<T>(
        val dataState: DataState,
        val data: T? = null,
        val type: Int = -1,
        val throwable: Throwable? = null
)