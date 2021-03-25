package com.android.task.domain.repository

import com.android.task.domain.base.DomainResponse
import com.android.task.domain.model.VehicleModel
import com.android.task.domain.model.VehicleNoteModel


interface VehicleRepositoryInterface {

    suspend fun getVehicles(): DomainResponse<List<VehicleModel>>

    suspend fun  getVehiclesNote(): DomainResponse<List<VehicleNoteModel>>

}