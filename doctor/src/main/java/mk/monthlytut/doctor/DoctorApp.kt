package mk.monthlytut.doctor

import android.app.Application
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.impl.DoctorModelImpl

class DoctorApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        DoctorModelImpl.initDatabase(applicationContext)
        SessionManager.init(applicationContext)
    }
}