package com.example.sgrailwaysystem.service

import com.example.sgrailwaysystem.model.Line
import com.example.sgrailwaysystem.model.RailwayMap
import com.example.sgrailwaysystem.model.Station
import com.example.sgrailwaysystem.model.rule.Rule

class RouteFinder(
        val rule: Rule,
        private val railwayMap: RailwayMap
) {
    fun findShortestPath(
            source: Station,
            destination: Station
    ): Pair<MutableList<Station>, Int> {

        if (source == destination) {
            return Pair(mutableListOf(), 0)
        }
        return findShortestPathUtil(source,
                destination,
                mutableListOf(),
                0,
                Line.CC,
                Int.MAX_VALUE,
                mutableListOf(),
                mutableMapOf())
    }

    private fun findShortestPathUtil(
            currentSource: Station,
            destination: Station,
            currentPath: MutableList<Station>,
            currentTime: Int,
            currentLine: Line,
            shortestTime: Int,
            shortestPath: MutableList<Station>,
            visited: MutableMap<Station, Boolean>
    ): Pair<MutableList<Station>, Int> {
        var shortestTimeInternal = shortestTime
        var shortestPathInternal = shortestPath
        val newCurrentTime = currentTime + getTime()
        visited[currentSource] = true

        //TODO: Introduce memoization for optimisation (current solution is slow)
        //Expected time complexity BigO(vertices*edges)
        currentPath.add(currentSource)
        if (currentSource == destination) {
            if (currentTime < shortestTime) {
                shortestTimeInternal = newCurrentTime
                shortestPathInternal = currentPath.toMutableList()
                visited[currentSource] = false
                currentPath.remove(currentSource)
                return Pair(shortestPathInternal, shortestTimeInternal)
            }
            visited[currentSource] = false
            currentPath.remove(currentSource)
        }
        val possiblePaths = railwayMap.getSourceDestinationMap()[currentSource]

        if (possiblePaths != null && possiblePaths.isNotEmpty()) {
            possiblePaths.forEach {
                if (visited[it] == null || !visited[it]!!) {
                    val currentResult = findShortestPathUtil(
                            it,
                            destination,
                            currentPath,
                            newCurrentTime,
                            currentLine,
                            shortestTimeInternal,
                            shortestPathInternal,
                            visited,
                    )
                    shortestPathInternal = currentResult.first
                    shortestTimeInternal = currentResult.second
                }
            }
        }
        visited[currentSource] = false
        currentPath.remove(currentSource)

        return Pair(shortestPathInternal, shortestTimeInternal)
    }


    private fun getTime(): Int {
        //Enhance it to find time based on rule
        return 1
    }
}