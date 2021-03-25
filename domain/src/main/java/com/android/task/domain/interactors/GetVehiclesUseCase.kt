package com.android.task.domain.interactors


import com.android.task.domain.base.DomainResponse
import com.android.task.domain.base.UseCase
import com.android.task.domain.model.VehicleModel
import com.android.task.domain.repository.VehicleRepositoryInterface
import javax.inject.Inject

class GetVehiclesUseCase @Inject constructor(
    private val vehicleRepositoryInterface: VehicleRepositoryInterface
) : UseCase<List<VehicleModel>> {
    override suspend fun execute(): DomainResponse<List<VehicleModel>> =
        vehicleRepositoryInterface.getVehicles()
}
