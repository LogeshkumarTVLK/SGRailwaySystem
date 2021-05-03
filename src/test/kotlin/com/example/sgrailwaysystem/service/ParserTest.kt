package com.example.sgrailwaysystem.service

import com.example.sgrailwaysystem.model.Line
import com.example.sgrailwaysystem.model.Station
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class ParserTest {
    @Test
    fun getSourceAndDestinationListShouldReturnsTwoEntries() {
        val parser = Parser()
        val stations = listOf(
                "NS1,Jurong East,10 March 1990",
                "NS2,Bukit Batok,10 March 1990"
        )
        val sourceDestinationMap = parser.getSourceDestinationList(stations)
        val source = Station(
                "Jurong East",
                Collections.singletonList(Line.NS)
        )
        val destination = Station(
                "Bukit Batok",
                Collections.singletonList(Line.NS)
        )
        assertEquals(2, sourceDestinationMap.size)
        assertEquals(destination, sourceDestinationMap[source]!![0])
        assertEquals(source, sourceDestinationMap[destination]!![0])
    }

    @Test
    fun getSourceAndDestinationListShouldReturnReturnThreeEntries() {
        val parser = Parser()
        val stations = listOf(
                "NS1,Jurong East,10 March 1990",
                "NS2,Bukit Batok,10 March 1990",
                "NS3,Buona Vista,12 March 1988"
        )
        val sourceDestinationMap = parser.getSourceDestinationList(stations)
        val juronEast = Station(
                "Jurong East",
                Collections.singletonList(Line.NS)
        )
        val bukitBatok = Station(
                "Bukit Batok",
                Collections.singletonList(Line.NS)
        )
        val buonaVista = Station(
                "Buona Vista",
                Collections.singletonList(Line.NS)
        )
        assertEquals(3, sourceDestinationMap.size)
        assertEquals(1, sourceDestinationMap[juronEast]!!.size)
        assertEquals(bukitBatok, sourceDestinationMap[juronEast]!![0])
        assertEquals(2, sourceDestinationMap[bukitBatok]!!.size)
        assertEquals(juronEast, sourceDestinationMap[bukitBatok]!![0])
        assertEquals(buonaVista, sourceDestinationMap[bukitBatok]!![1])
        assertEquals(1, sourceDestinationMap[buonaVista]!!.size)
        assertEquals(bukitBatok, sourceDestinationMap[buonaVista]!![0])
    }

    @Test
    fun getSourceAndDestinationListShouldReturnMultipleDestinationsPerStation() {
        val parser = Parser()
        val stations = listOf(
                "NS3,Buona Vista,12 March 1988",
                "NS4,Bukit batok,12 March 1988",
                "CC22,Buona Vista,8 October 2011",
                "CC23,One North,8 October 2011",
                "NE3,Buona Vista,12 March 1988",
                "NE4,Hougang,12 March 1988",
        )
        val sourceDestinationMap = parser.getSourceDestinationList(stations)
        val buonaVista = Station(
                "Buona Vista",
                mutableListOf(Line.NS, Line.CC, Line.NE)
        )
        val bukitBatok = Station(
                "Bukit batok",
                Collections.singletonList(Line.NS)
        )
        val oneNorth = Station(
                "One North",
                Collections.singletonList(Line.CC)
        )
        val hougang = Station(
                "Hougang",
                Collections.singletonList(Line.NE)
        )
        println(sourceDestinationMap)
        assertEquals(4, sourceDestinationMap.size)
        assertEquals(3, sourceDestinationMap[buonaVista]!!.size)
        assertEquals(bukitBatok, sourceDestinationMap[buonaVista]!![0])
        assertEquals(oneNorth, sourceDestinationMap[buonaVista]!![1])
        assertEquals(hougang, sourceDestinationMap[buonaVista]!![2])
        assertEquals(1, sourceDestinationMap[bukitBatok]!!.size)
        assertEquals(buonaVista, sourceDestinationMap[bukitBatok]!![0])
        assertEquals(1, sourceDestinationMap[oneNorth]!!.size)
        assertEquals(buonaVista, sourceDestinationMap[oneNorth]!![0])
        assertEquals(1, sourceDestinationMap[hougang]!!.size)
        assertEquals(buonaVista, sourceDestinationMap[hougang]!![0])
    }
}