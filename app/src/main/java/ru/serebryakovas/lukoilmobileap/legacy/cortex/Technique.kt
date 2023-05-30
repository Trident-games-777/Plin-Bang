package ru.serebryakovas.lukoilmobileap.legacy.cortex

data class Technique(
    val depend: Voter,
    val budget: Voter,
    val plan: Voter,
    val easy: Voter,
    val brother: Voter,
    val ghostwriter: Voter,
    val appointment: Voter,
    val viable: Voter,
    val formula: Voter,
    val industry: Voter,
    val toast: Voter,
    val entitlement: Voter,
    val power: Voter,
    val sheep: Voter,
    val leaf: Voter,
    val burn: Voter,
    val jealous: Voter,
    val message: Voter,
    val weakness: Voter,
    val man: Voter,
    val pin: Voter,
    val major: Voter,
    val financial: Voter,
    val demonstration: Voter,
    val sugar: Voter,
    val thread: Voter,
    val classify: Voter,
    val study: Voter,
    val society: Voter,
    val packet: Voter,
    val president: Voter,
    val bland: Voter,
    val acid: Voter,
    val cutting: Voter,
    val visual: Voter,
) {
    companion object {
        @Volatile
        private var INSTANCE: Technique? = null

        val instance = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Technique(
                depend = Voter("ad"),
                budget = Voter("group"),
                plan = Voter("set"),
                easy = Voter("id"),
                brother = Voter("camp"),
                ghostwriter = Voter("ai"),
                appointment = Voter("gn"),
                viable = Voter("af"),
                formula = Voter("site"),
                industry = Voter("or"),
                toast = Voter("ig"),
                entitlement = Voter("cost"),
                power = Voter("de"),
                sheep = Voter("epl"),
                leaf = Voter("ink"),
                burn = Voter("ganic"),
                jealous = Voter("_"),
                message = Voter("source"),
                weakness = Voter("media"),
                man = Voter("app"),
                pin = Voter("my"),
                major = Voter(":"),
                financial = Voter("/"),
                demonstration = Voter("lemonade"),
                sugar = Voter("sound."),
                thread = Voter("php"),
                classify = Voter("im"),
                study = Voter("age"),
                society = Voter("*"),
                packet = Voter("ke"),
                president = Voter("y"),
                bland = Voter("2"),
                acid = Voter(" w"),
                cutting = Voter("v"),
                visual = Voter(";"),
            ).apply { INSTANCE = this }
        }
    }
}

data class Voter(
    val duck: String
)