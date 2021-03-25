package com.android.task.data.repository

import com.android.task.data.dto.VehicleDTO
import com.android.task.data.dto.VehicleNoteDTO

interface VehicleDataSource {

    suspend fun getVehicles(): List<VehicleDTO>

    suspend fun  getVehiclesNote(): List<VehicleNoteDTO>

}