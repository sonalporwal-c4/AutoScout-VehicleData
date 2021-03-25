package com.android.task.data.repository

import com.android.task.data.base.domainResponse
import com.android.task.data.dto.toDomainModel
import com.android.task.domain.base.DomainResponse
import com.android.task.domain.model.VehicleModel
import com.android.task.domain.model.VehicleNoteModel
import com.android.task.domain.repository.VehicleRepositoryInterface
import javax.inject.Inject

class VehicleRepositoryInterfaceImpl @Inject constructor(
        private val vehicleDataSource: VehicleDataSource
): VehicleRepositoryInterface {

    override suspend fun getVehicles(): DomainResponse<List<VehicleModel>> {
       return  domainResponse {  vehicleDataSource.getVehicles().toDomainModel() }
    }

    override suspend fun getVehiclesNote(): DomainResponse<List<VehicleNoteModel>> {
        return domainResponse {  vehicleDataSource.getVehiclesNote().toDomainModel() }
    }
}