package com.example.sgrailwaysystem.service

import com.example.sgrailwaysystem.model.Line
import com.example.sgrailwaysystem.model.Station

//Parser to parse from csv file
class Parser {

    fun getSourceDestinationList(
            stationsCsvString: List<String>
    ): Map<Station, List<Station>> {
        val stationMap = buildStationsWithLines(stationsCsvString)

        return mapSourceAndDestinations(stationsCsvString, stationMap)
    }

    private fun mapSourceAndDestinations(stationsCsvString: List<String>, stationMap: MutableMap<String, Station>): MutableMap<Station, MutableList<Station>> {
        val sourceDestinationsMap = mutableMapOf<Station, MutableList<Station>>()

        var previousStation: Station? = null
        var previousStationLine: Line? = null

        stationsCsvString.forEach {
            val stationDetails = it.split(",")
            val currentStationLine = getLine(stationDetails[0])
            val currentStationName = stationDetails[1]
            val currentStation = stationMap.get(currentStationName)!!
            if (previousStation == null || currentStationLine != previousStationLine) {
                previousStation = currentStation
                previousStationLine = currentStationLine
            } else {
                if (sourceDestinationsMap[previousStation] != null) {
                    sourceDestinationsMap[previousStation]!!.add(currentStation)
                } else {
                    sourceDestinationsMap[previousStation!!] = mutableListOf(currentStation)
                }
                if (sourceDestinationsMap[currentStation] != null) {
                    sourceDestinationsMap[currentStation]!!.add(previousStation!!)
                } else {
                    sourceDestinationsMap[currentStation] = mutableListOf(previousStation!!)
                }
                previousStation = currentStation
                previousStationLine = currentStationLine
            }
        }
        return sourceDestinationsMap
    }

    fun buildStationsWithLines(stationsCsvString: List<String>): MutableMap<String, Station> {
        val stationMap = mutableMapOf<String, Station>()
        stationsCsvString.forEach {
            val stationDetails = it.split(",")
            val line = getLine(stationDetails[0])
            val stationName = stationDetails[1]
            if (stationMap.containsKey(stationName)) {
                val station = stationMap[stationName]
                station!!.lines.add(line)
            } else {
                stationMap[stationName] = Station(
                        stationName,
                        mutableListOf(line)
                )
            }
        }
        return stationMap
    }

    private fun getLine(
            line: String
    ): Line {
        when {
            line.contains("NS") -> {
                return Line.NS
            }
            line.contains("NE") -> {
                return Line.NE
            }
            line.contains("EW") -> {
                return Line.EW
            }
            line.contains("CC") -> {
                return Line.CC
            }
            line.contains("CE") -> {
                return Line.CE
            }
            line.contains("DT") -> {
                return Line.DT
            }
            line.contains("TE") -> {
                return Line.TE
            }
            else -> {
                return Line.CG
            }
        }
    }
}