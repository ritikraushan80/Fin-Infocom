package com.fininfocom.fininfocomapplication.Model

/**
 * Created by Ritik on 2/8/2024.
 */
data class UserData(
    val name: String,
    val age: Int,
    val city: String
)

val userList = listOf(
    UserData("Ritik", 25, "Noida"),
    UserData("Amit kumar", 30, "Delhi"),
    UserData("Gajendra kumar", 25, "Noida"),
    UserData("Rajeev kumar", 30, "Delhi"),
    UserData("Niraj kumar", 25, "Patna")
)