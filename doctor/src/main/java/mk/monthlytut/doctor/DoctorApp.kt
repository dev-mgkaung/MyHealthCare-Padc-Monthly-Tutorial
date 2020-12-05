package mk.monthlytut.doctor

import android.app.Application
import mk.padc.share.persistances.MyCareDatabase

class DoctorApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        MyCareDatabase.getDBInstance(applicationContext)
    }
}