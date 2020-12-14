package mk.monthlytut.patient.delegates

import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.RecentDoctorVO

interface RecentDoctorViewItemActionDelegate {
    fun onTapRecentDoctor(doctorVO: RecentDoctorVO)
}