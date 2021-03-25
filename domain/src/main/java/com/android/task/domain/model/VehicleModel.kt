package com.android.task.domain.model

import java.util.*

data class VehicleModel(
   val id: Int,
   val make: String?,
   val model: String?,
   val colour: String?,
   val price: String?,
   val firstRegistration: String?,
   val mileage: Long?,
   val fuel: String?,
   val seller: SellerModel?,
   val images: List<ImageModel>?,
   val description: String?,
   val notes:String?
)

data class ImageModel (
   val url: String?
)

data class SellerModel(
   val type: String?,
   val phone: String?,
   val city: String?
)
