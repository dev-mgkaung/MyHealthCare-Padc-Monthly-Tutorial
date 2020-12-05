package mk.monthlytut.doctor

import android.app.Application
import mk.padc.share.data.models.impl.DoctorModelImpl

class DoctorApp  : Application()
{
    override fun onCreate() {
        super.onCreate()
        DoctorModelImpl.initDatabase(applicationContext)
    }
}