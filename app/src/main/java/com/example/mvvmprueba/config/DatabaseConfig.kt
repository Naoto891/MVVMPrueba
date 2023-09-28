package com.example.mvvmprueba.config

import org.jetbrains.exposed.sql.Database

object DatabaseConfig {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://137.184.120.127:5432/sigcon",
            driver = "org.postgresql.Driver",
            user = "modulo4",
            password = "modulo4"
        )
    }
}