package com.android.task.data.dto

import com.android.task.domain.model.ImageModel
import com.android.task.domain.model.SellerModel
import com.android.task.domain.model.VehicleModel


/* Response of Vehicle Details */
data class VehicleDTO(
    val id: Int,
    val make: String?,
    val model: String?,
    val colour: String?,
    val price: String?,
    val firstRegistration: String?,
    val mileage: Long?,
    val fuel: String?,
    val seller: SellerDTO?,
    val images: List<ImageDTO>?,
    val description: String?,
)

data class ImageDTO (
    val url: String?
)

data class SellerDTO(
    val type: String?,
    val phone: String?,
    val city: String?
)

fun List<VehicleDTO>.toDomainModel(): List<VehicleModel> {
    var vehicleList = mutableListOf<VehicleModel>()
    forEach {
        vehicleList.add(
            VehicleModel(
                id = it.id,
                make = it.make,
                model = it.model,
                colour = it.colour,
                price = it.price,
                firstRegistration = it.firstRegistration,
                mileage = it.mileage,
                fuel = it.fuel,
                seller = it.seller?.toDomainModel(),
                images = it.images?.toDomainModel(),
                description = it.description,
                notes = null
            )
        )
    }
    return vehicleList
}

@JvmName("toDomainModelImageDTO")
fun List<ImageDTO>.toDomainModel(): List<ImageModel> {
    var imageList = mutableListOf<ImageModel>()
    forEach {
        imageList.add(ImageModel(url = it.url))
    }
    return imageList
}

fun SellerDTO.toDomainModel(): SellerModel = SellerModel(type, phone, city)