package com.example.sgrailwaysystem.service

import com.example.sgrailwaysystem.model.Line
import com.example.sgrailwaysystem.model.RailwayMap
import com.example.sgrailwaysystem.model.Station
import com.example.sgrailwaysystem.model.rule.PeakHoursRule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RouteFinderTest {

    @Test
    fun findShortestPathShouldReturnShortestPathsScenario1() {
        val a = Station("a", mutableListOf(Line.CC))
        val b = Station("b", mutableListOf(Line.CC))
        val c = Station("c", mutableListOf(Line.CC))
        val d = Station("d", mutableListOf(Line.CC))
        val e = Station("e", mutableListOf(Line.CC))
        val sourceToDestinationMap = mapOf(
                a to listOf(b, c),
                b to listOf(d, e),
                d to listOf(e)
        )
        val railwayMap = RailwayMap(sourceToDestinationMap)
        val routeFinder = RouteFinder(
                PeakHoursRule(),
                railwayMap
        )
        val findShortestPath = routeFinder.findShortestPath(
                a,
                e
        )
        assertEquals(3, findShortestPath.first.size)
        assertEquals(a, findShortestPath.first[0])
        assertEquals(b, findShortestPath.first[1])
        assertEquals(e, findShortestPath.first[2])
        assertEquals(3, findShortestPath.second)
    }

    @Test
    fun findShortestPathShouldReturnShortestPathsScenario2() {
        val a = Station("a", mutableListOf(Line.CC))
        val b = Station("b", mutableListOf(Line.CC))
        val c = Station("c", mutableListOf(Line.CC))
        val d = Station("d", mutableListOf(Line.CC))
        val e = Station("e", mutableListOf(Line.CC))
        val f = Station("f", mutableListOf(Line.CC))
        val sourceToDestinationMap = mapOf(
                a to listOf(b, f, c),
                b to listOf(d),
                c to listOf(e)
        )
        val railwayMap = RailwayMap(sourceToDestinationMap)
        val routeFinder = RouteFinder(
                PeakHoursRule(),
                railwayMap
        )
        val findShortestPath = routeFinder.findShortestPath(
                a,
                f
        )
        assertEquals(2, findShortestPath.first.size)
        assertEquals(a, findShortestPath.first[0])
        assertEquals(f, findShortestPath.first[1])
        assertEquals(2, findShortestPath.second)
    }

    @Test
    fun findShortestPathShouldReturnEmptyListOnSameSourceAndDestination() {
        val a = Station("a", mutableListOf(Line.CC))
        val b = Station("b", mutableListOf(Line.CC))
        val c = Station("c", mutableListOf(Line.CC))
        val d = Station("d", mutableListOf(Line.CC))
        val e = Station("e", mutableListOf(Line.CC))
        val sourceToDestinationMap = mapOf(
                a to listOf(b, c),
                b to listOf(d, e),
                d to listOf(e)
        )
        val railwayMap = RailwayMap(sourceToDestinationMap)
        val routeFinder = RouteFinder(
                PeakHoursRule(),
                railwayMap
        )
        val findShortestPath = routeFinder.findShortestPath(
                a,
                a
        )
        assertEquals(emptyList<Station>(), findShortestPath.first)
        assertEquals(0, findShortestPath.second)
    }
}