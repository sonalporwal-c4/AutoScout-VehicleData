package com.android.task.data.repository

import com.android.task.data.dto.ImageDTO
import com.android.task.data.dto.SellerDTO
import com.android.task.data.dto.VehicleDTO
import com.android.task.data.dto.VehicleNoteDTO

fun getVehicleList(): List<VehicleDTO> {
    return listOf(VehicleDTO(
            id =  1,
            make = "BMW",
            model = "316i",
            price =  "13000",
            colour = "White",
            firstRegistration =  "01-2000",
            mileage = 25000,
            fuel =  "Gasoline",
            images = listOf(ImageDTO(
                    url = "https://loremflickr.com/g/320/240/bmw")),
            description = "Almost like new. Full service history",
            seller = SellerDTO(
                    type = "Private",
                    phone = "+123456789",
                    city = "Stuttgart")))
}


fun getVehicleNoteList(): List<VehicleNoteDTO> {
    return listOf(VehicleNoteDTO(
            vehicleId = 2,
            note = "good price"
           ))
}