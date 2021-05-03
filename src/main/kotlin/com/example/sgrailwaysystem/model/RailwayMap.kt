package com.example.sgrailwaysystem.model

class RailwayMap(
        private val sourceDestinationMap: Map<Station, List<Station>>
) {
    fun getDestinations(source: Station) = sourceDestinationMap[source]

    fun getSourceDestinationMap() = sourceDestinationMap
}