package com.example.sgrailwaysystem.model.rule

import com.example.sgrailwaysystem.model.Line

open class Rule(
        val waitingTime: Int = 10,
        val defaultTimeTaken: Int = 10,
        val nonOperatingLines : List<Line> = emptyList()
)