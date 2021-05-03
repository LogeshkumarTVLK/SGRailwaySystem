package com.example.sgrailwaysystem.model.rule

import com.example.sgrailwaysystem.model.Line

class NightHoursRule(
        val timeTakenMap: Map<Line, Int> = mapOf(
                Line.TE to 8
        )
) : Rule(
        nonOperatingLines = listOf(
                Line.DT,
                Line.CG,
                Line.CE,
        )
)