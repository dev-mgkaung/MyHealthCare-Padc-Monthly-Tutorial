package mk.padc.share

import android.app.Application
import mk.podcast.com.persistances.MyCareDatabase

class MyCareApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        MyCareDatabase.getDBInstance(applicationContext)
    }
}