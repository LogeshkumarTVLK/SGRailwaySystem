package com.example.sgrailwaysystem.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class RailwayMapTest {
    private val sourceToDestinationMap =  mapOf(
            Station("a", mutableListOf(Line.CC))
                    to
            listOf(Station("a", mutableListOf(Line.CC))
            )
    )

    @Test
    fun getDestinationsShouldReturnDestinations() {
        val railwayMap = RailwayMap(sourceToDestinationMap)
        val destinations = railwayMap.getDestinations(
                Station("a", mutableListOf(Line.CC))
        )
        assertEquals(listOf(Station("a", mutableListOf(Line.CC))), destinations)
    }

    @Test
    fun getDestinationsShouldReturnNullIfNotFound() {
        val railwayMap = RailwayMap(sourceToDestinationMap)
        val destinations = railwayMap.getDestinations(
                Station("b", mutableListOf(Line.CC))
        )
        assertNull(destinations)
    }
}