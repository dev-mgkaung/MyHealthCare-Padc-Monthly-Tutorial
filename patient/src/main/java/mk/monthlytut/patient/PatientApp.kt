package mk.monthlytut.patient

import android.app.Application
import mk.padc.share.data.models.impl.PatientModelImpl


class PatientApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        PatientModelImpl.initDatabase(applicationContext)
    }
}