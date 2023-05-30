package ru.serebryakovas.lukoilmobileap.legacy.garden

fun interface GardenCall {
    fun onReceive(gardens: List<Garden>)
}