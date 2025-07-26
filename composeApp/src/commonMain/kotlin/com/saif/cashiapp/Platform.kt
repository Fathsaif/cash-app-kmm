package com.saif.cashiapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform