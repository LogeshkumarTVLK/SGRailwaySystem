package com.example.sgrailwaysystem.service

import com.example.sgrailwaysystem.model.Line
import com.example.sgrailwaysystem.model.Station
import java.lang.StringBuilder

class ResultGenerator {
    fun generate(
            shortestPath: List<Station>,
            startLine: Line
    ): String {
        
        if (shortestPath.size < 2)
            return ""

        val result = StringBuilder()
        var start = shortestPath[0]
        var currentLine = startLine
        for (i in 1 until shortestPath.size) {
            val destination = shortestPath[i]
            if(destination.lines.contains(currentLine)){
                result.append(
                        "Take "+ currentLine.name+ " line from " + start.name +" to "+ destination.name +"\n"
                )
            }else{
                result.append(
                        "Change from "+ currentLine.name+ " line to "+ destination.lines[0].name +" line\n"
                )
                currentLine = destination.lines[0]
                result.append(
                        "Take "+ currentLine.name+ " line from " + start.name +" to "+ destination.name +"\n"
                )
            }
            start = destination
        }
        return result.toString()
    }
}