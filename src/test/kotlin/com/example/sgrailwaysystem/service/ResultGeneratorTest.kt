package com.example.sgrailwaysystem.service

import com.example.sgrailwaysystem.model.Line
import com.example.sgrailwaysystem.model.Station
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ResultGeneratorTest {

    @Test
    fun testSuccessfulScenario() {
        val a = Station("a", mutableListOf(Line.CC))
        val b = Station("b", mutableListOf(Line.CC, Line.DT))
        val c = Station("c", mutableListOf(Line.DT))
        val d = Station("d", mutableListOf(Line.DT, Line.CE))
        val e = Station("e", mutableListOf(Line.CE))

        val shortestPath = listOf(a, b, c, d, e)
        val resultGenerator = ResultGenerator()
        val result = resultGenerator.generate(
                shortestPath,
                Line.CC
        )
        assertEquals(
                "Take CC line from a to b\n" +
                        "Change from CC line to DT line\n" +
                        "Take DT line from b to c\n" +
                        "Take DT line from c to d\n" +
                        "Change from DT line to CE line\n" +
                        "Take CE line from d to e\n",
                result
        )
    }

    @Test
    fun shouldReturnEmptyStringIfSizeOfStationIsLessThan2() {
        val a = Station("a", mutableListOf(Line.CC))

        val shortestPath = listOf(a)
        val resultGenerator = ResultGenerator()
        val result = resultGenerator.generate(
                shortestPath,
                Line.CC
        )
        assertEquals(
                "",
                result
        )
    }
}