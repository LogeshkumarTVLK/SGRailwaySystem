package com.example.sgrailwaysystem.model

class RailwayMap(
        private val sourceDestinationMap: Map<Station, List<Station>>
) {
    fun getDestinations(source: Station) = sourceDestinationMap[source]

    fun getSourceDestinationMap() = sourceDestinationMap

    //TODO: move the method from route finder to railway map,.
    // so that the data will not be exposed.
}