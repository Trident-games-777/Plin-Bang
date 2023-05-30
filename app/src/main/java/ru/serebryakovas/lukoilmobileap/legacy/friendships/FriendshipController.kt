package ru.serebryakovas.lukoilmobileap.legacy.friendships

import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.legacy.controllers.Pound
import ru.serebryakovas.lukoilmobileap.legacy.fluffygames.Scale
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenCall

class FriendshipController : GardenCall {

    private var scale: Scale? = null
    private var aluminium: Aluminium? = null

    fun register() {
        scale = Scale()
        aluminium = Aluminium()
        GardenLand.registerCall(this)
    }

    fun unregister() {
        GardenLand.unregisterCall(this)
    }

    override fun onReceive(gardens: List<Garden>) {
        when(gardens.size) {
            2 -> {
                aluminium?.rescue(PlinBangActivity.getInstance())
            }
            3 -> {
                if (gardens.last().bed == null) {
                    scale?.sharp()
                } else {
                    GardenLand.addGarden(Garden())
                    GardenLand.addGarden(Garden())
                }
            }
            5 -> {
                Pound(gardens).computer()
            }
        }
    }

}