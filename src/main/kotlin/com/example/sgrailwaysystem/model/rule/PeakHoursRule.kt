package com.example.sgrailwaysystem.model.rule

import com.example.sgrailwaysystem.model.Line

class PeakHoursRule(
        val timeTakenMap: Map<Line, Int> = mapOf(
                Line.NS to 12,
                Line.NE to 12
        )
) : Rule(
        waitingTime = 15
)