package ru.serebryakovas.lukoilmobileap.plinbag

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class PlinBag(
    context: Context
) {

    private val plinBag: PlinBagDb

    init {
        val driver = AndroidSqliteDriver(PlinBagDb.Schema, context, "plin_bag.db")
        plinBag = PlinBagDb(driver)
    }

    fun get(): Plin? {
        return plinBag.plin_bagQueries.selectAll().executeAsList().firstOrNull()
    }

    fun insert(plin: Plin) {
        if (get() == null) {
            plinBag.plin_bagQueries.insertPlinObject(plin)
        }
    }

}