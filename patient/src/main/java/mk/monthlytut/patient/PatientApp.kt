package mk.monthlytut.patient

import android.app.Application
import mk.podcast.com.persistances.MyCareDatabase

class PatientApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        MyCareDatabase.getDBInstance(applicationContext)
    }
}