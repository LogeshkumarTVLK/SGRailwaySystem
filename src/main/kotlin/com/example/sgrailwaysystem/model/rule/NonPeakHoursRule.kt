package com.example.sgrailwaysystem.model.rule

import com.example.sgrailwaysystem.model.Line

class NonPeakHoursRule(
        val timeTakenMap: Map<Line, Int> = mapOf(
                Line.DT to 8,
                Line.TE to 8
        )
) : Rule(
)