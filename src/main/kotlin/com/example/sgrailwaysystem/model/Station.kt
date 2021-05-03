package com.example.sgrailwaysystem.model

data class Station(
   val name: String,
   val lines: MutableList<Line>
)