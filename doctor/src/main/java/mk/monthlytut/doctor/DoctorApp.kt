package mk.monthlytut.doctor

import android.app.Application
import mk.podcast.com.persistances.MyCareDatabase

class DoctorApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        MyCareDatabase.getDBInstance(applicationContext)
    }
}