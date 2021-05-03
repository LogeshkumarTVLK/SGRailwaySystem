package com.example.sgrailwaysystem

import com.example.sgrailwaysystem.model.RailwayMap
import com.example.sgrailwaysystem.model.rule.PeakHoursRule
import com.example.sgrailwaysystem.service.Parser
import com.example.sgrailwaysystem.service.ResultGenerator
import com.example.sgrailwaysystem.service.RouteFinder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.net.URL
import kotlin.system.exitProcess

@SpringBootApplication
class SgrailwaysystemApplication

fun main(args: Array<String>) {
	//TODO: Modify it to use REST api's

    val file = File("./src/main/kotlin/com/example/sgrailwaysystem/stations.csv")
    //TODO: store it in memory so dont have to parse everytime
    val stationsString = mutableListOf<String>()
    file.forEachLine {
       stationsString.add(it)
    }
    val parser = Parser()
    val listOfAvailableStations = parser.buildStationsWithLines(stationsString)
    println("Enter your source: ")
    val source = listOfAvailableStations[readLine()]
    println("Enter your destination: ")
    val destination = listOfAvailableStations[readLine()]
    if(source == null){
        println("Source shouldn't be null")
        exitProcess(1)
    }
    if(destination == null){
        println("Destination shouldn't be null")
        exitProcess(1)
    }
    val sourceDestinationList = parser.getSourceDestinationList(stationsString)
    val railwayMap = RailwayMap(sourceDestinationList)
    val routeFinder = RouteFinder(PeakHoursRule(), railwayMap)
    val shortestPath = routeFinder.findShortestPath(source, destination)
    println("Your shortest path is, \n")
    println(ResultGenerator().generate(shortestPath.first, source.lines[0]))

//	runApplication<SgrailwaysystemApplication>(*args)
}
