package com.proptit.btl_oop.model

data class CoffeeCategory(
    val id: Int = 0,
    val name: String = ""
){
    constructor(): this(0, "")
}