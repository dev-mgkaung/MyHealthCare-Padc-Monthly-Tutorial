package mk.padc.share.data.models

import android.content.Context
import mk.padc.share.persistances.MyCareDatabase

abstract class BaseModel  {

    protected lateinit var mTheDB: MyCareDatabase


    fun initDatabase(context: Context) {
        mTheDB = MyCareDatabase.getDBInstance(context)
    }

}