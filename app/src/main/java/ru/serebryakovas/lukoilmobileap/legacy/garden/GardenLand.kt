package ru.serebryakovas.lukoilmobileap.legacy.garden

object GardenLand {

    private val calls: MutableList<GardenCall?> = mutableListOf()
    private val gardens: MutableList<Garden> = mutableListOf()

    fun registerCall(call: GardenCall) {
        calls.add(call)
        call.onReceive(gardens)
    }

    fun unregisterCall(call: GardenCall) {
        calls.remove(call)
    }

    fun addGarden(garden: Garden) {
        gardens.add(garden)
        calls.forEach { it?.onReceive(gardens) }
    }

    fun clear() {
        gardens.clear()
        calls.clear()
    }
}

data class Garden(
    val bed: Any? = null
)

