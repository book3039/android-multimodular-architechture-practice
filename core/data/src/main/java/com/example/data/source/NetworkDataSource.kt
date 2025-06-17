package com.example.data.source

import com.example.data.connectivity.NetworkMonitorInterface
import com.google.gson.Gson

class NetworkDataSource<SERVICE>(
    private val service: SERVICE,
    private val gson: Gson,
    private val networkMonitorInterface: NetworkMonitorInterface,
    private val userIdProvider: () -> String,
)