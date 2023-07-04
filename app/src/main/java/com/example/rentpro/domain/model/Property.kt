package com.example.rentpro.domain.model

import com.google.firebase.Timestamp

data class Property(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val rent: Double = 0.0,
    val location: String = "",
    val numBathroom: Int = 0,
    val numBedroom: Int = 0,
    val size: Double = 0.0,
    val rating: Int = 0,
    val imagePath: String = "",
    val isAvailable: Boolean = true,
    val landlord: String = "",
    val created_at: Timestamp = Timestamp.now(),
){
    constructor() : this(
        id = "",
        title = "",
        description = "",
        rent = 0.0,
        location = "",
        numBathroom = 0,
        numBedroom = 0,
        size = 0.0,
        rating = 0,
        landlord = "",
        created_at = Timestamp.now()
    )
}
