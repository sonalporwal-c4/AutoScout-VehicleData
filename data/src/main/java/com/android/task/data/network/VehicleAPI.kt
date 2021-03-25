package com.android.task.data.network

import com.android.task.data.dto.VehicleDTO
import com.android.task.data.dto.VehicleNoteDTO
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface VehicleAPI {

    @GET("http://private-fe87c-simpleclassifieds.apiary-mock.com")
    suspend fun getVehicles(): Response<List<VehicleDTO>>

    @GET("https://private-e7c3d8-classifiednotes.apiary-mock.com")
    suspend fun getVehiclesNote(): Response<List<VehicleNoteDTO>>

}