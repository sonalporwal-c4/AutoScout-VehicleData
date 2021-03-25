package com.android.task.data.repository

import com.android.task.data.base.returnOrThrow
import com.android.task.data.dto.VehicleDTO
import com.android.task.data.dto.VehicleNoteDTO
import com.android.task.data.network.VehicleAPI
import javax.inject.Inject

class VehicleDataSourceImpl @Inject constructor(private val vehicleApi: VehicleAPI) :
    VehicleDataSource {

    override suspend fun getVehicles(): List<VehicleDTO> {
        return returnOrThrow { vehicleApi.getVehicles() }
    }

    override suspend fun getVehiclesNote(): List<VehicleNoteDTO> {
        return returnOrThrow { vehicleApi.getVehiclesNote() }
    }

}