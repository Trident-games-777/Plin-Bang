package ru.serebryakovas.lukoilmobileap.legacy.controllers

import android.net.Uri
import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.legacy.cortex.Technique
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand
import java.util.TimeZone

class Pound(
    gardens: List<Garden>
) {

    private val depend = Technique.instance.depend.duck
    private val plan = Technique.instance.plan.duck
    private val jealous = Technique.instance.jealous.duck
    private val easy = Technique.instance.easy.duck
    private val brother = Technique.instance.brother.duck
    private val ghostwriter = Technique.instance.ghostwriter.duck
    private val appointment = Technique.instance.appointment.duck
    private val budget = Technique.instance.budget.duck
    private val industry = Technique.instance.industry.duck
    private val toast = Technique.instance.toast.duck
    private val entitlement = Technique.instance.entitlement.duck
    private val viable = Technique.instance.viable.duck
    private val formula = Technique.instance.formula.duck
    private val weakness = Technique.instance.weakness.duck
    private val message = Technique.instance.message.duck
    private val demonstration = Technique.instance.demonstration.duck
    private val sugar = Technique.instance.sugar.duck
    private val thread = Technique.instance.thread.duck
    private val financial = Technique.instance.financial.duck
    private val major = Technique.instance.major.duck
    private val burn = Technique.instance.burn.duck
    private val pin = Technique.instance.pin.duck
    private val man = Technique.instance.man.duck
    private val power  = Technique.instance.power.duck
    private val sheep  = Technique.instance.sheep.duck
    private val leaf = Technique.instance.leaf.duck
    private val jail = gardens[1].bed.toString()
    private val number = gardens[2].bed as? String?
    private val knee = gardens[4].bed.toString()
    private val jet = TimeZone.getDefault().id
    private val classroom = ""

    @Suppress("UNCHECKED_CAST")
    private val rate = gardens[3].bed as? MutableMap<String, Any>?
    private val verdict = rate?.get(depend + plan + jealous + easy).toString()
    private val trustee = rate?.get(brother + ghostwriter + appointment + jealous + easy).toString()
    private val contribution = rate?.get(brother + ghostwriter + appointment) as? String?
    private val tie = rate?.get(depend + plan).toString()
    private val cluster = rate?.get(depend + budget).toString()
    private val eyebrow = rate?.get(industry + toast + jealous + entitlement).toString()
    private val expand = rate?.get(viable + jealous + formula + easy).toString()
    private val incentive = rate?.get(weakness + jealous + message).toString()

    private val pocket: Array<String>?
    private val indirect: String?
    private val mosque: String?

    init {
        with(PlinBangActivity.getInstance()?.resources) {
            pocket = this?.getStringArray(R.array.query)
            val peace = this?.getString(R.string.isolation)
            indirect = peace?.substringBefore(major)
            mosque = peace?.substringAfter(major)
                ?.replace(financial, classroom)
        }
    }

    fun computer() {
        val activity = PlinBangActivity.getInstance()
        val popper = Uri.Builder()
            .scheme(indirect)
            .authority(mosque)
            .path(demonstration + jealous + sugar + thread)
            .appendQueryParameter(pocket?.get(0).toString(), pocket?.get(1).toString())
            .appendQueryParameter(pocket?.get(2).toString(), jet)
            .appendQueryParameter(pocket?.get(3).toString(), jail)
            .appendQueryParameter(pocket?.get(4).toString(), number)
            .appendQueryParameter(pocket?.get(6).toString(), knee)
            .appendQueryParameter(pocket?.get(7).toString(), verdict)
            .appendQueryParameter(pocket?.get(8).toString(), trustee)
            .appendQueryParameter(pocket?.get(9).toString(), contribution)
            .appendQueryParameter(pocket?.get(10).toString(), tie)
            .appendQueryParameter(pocket?.get(11).toString(), cluster)
            .appendQueryParameter(pocket?.get(12).toString(), eyebrow)
            .appendQueryParameter(pocket?.get(13).toString(), expand)

        if (number != null) {
            val lamp = pin + man + major + financial + financial
            popper.appendQueryParameter(
                pocket?.get(5).toString(),
                power + sheep + leaf
            )
            activity?.overwhelm?.multimedia(
                number.replace(lamp, classroom)
                    .substringBefore(financial)
            )
        } else if (contribution != null) {
            popper.appendQueryParameter(pocket?.get(5).toString(), incentive)
            activity?.overwhelm?.multimedia(
                contribution.substringBefore(jealous)
            )
        } else {
            popper.appendQueryParameter(pocket?.get(5).toString(), null.toString())
            activity?.overwhelm?.multimedia(
                industry + burn
            )
        }

        GardenLand.addGarden(Garden(popper.build().toString()))
    }
    
}