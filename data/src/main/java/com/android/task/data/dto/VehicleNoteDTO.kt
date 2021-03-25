package com.android.task.data.dto

import com.android.task.domain.model.ImageModel
import com.android.task.domain.model.SellerModel
import com.android.task.domain.model.VehicleModel
import com.android.task.domain.model.VehicleNoteModel


/* Response of Vehicle Notes */
data class VehicleNoteDTO(
    val vehicleId: Int,
    val note: String
)

fun List<VehicleNoteDTO>.toDomainModel(): List<VehicleNoteModel> {
    var vehicleNoteList = mutableListOf<VehicleNoteModel>()
    forEach {
        vehicleNoteList.add(
            VehicleNoteModel(
                vehicleId = it.vehicleId,
                note = it.note)
        )
    }
    return vehicleNoteList
}