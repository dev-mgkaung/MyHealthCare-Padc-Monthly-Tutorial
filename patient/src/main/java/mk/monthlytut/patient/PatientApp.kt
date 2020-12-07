package mk.monthlytut.patient

import android.app.Application
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.data.models.impl.PatientModelImpl


class PatientApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        PatientModelImpl.initDatabase(applicationContext)
        SessionManager.init(applicationContext)
    }
}