package com.example.animationstestapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val name: String = "",
    val surname: String = "",
    val email: String  = "",
    val password: String  = "",
    val phoneNumber: String  = "",
    val postalAddress: String  = ""
) {

}