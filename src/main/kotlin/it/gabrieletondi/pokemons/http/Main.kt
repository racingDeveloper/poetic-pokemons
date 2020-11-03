package it.gabrieletondi.pokemons.http

import spark.kotlin.ignite

fun main() {
    val http = ignite()

    http.get("/ping") {
        "pong"
    }
}