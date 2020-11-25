package mk.padc.share

import android.app.Application
import mk.padc.share.persistances.MyCareDatabase

class MyCareApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        MyCareDatabase.getDBInstance(applicationContext)
    }
}