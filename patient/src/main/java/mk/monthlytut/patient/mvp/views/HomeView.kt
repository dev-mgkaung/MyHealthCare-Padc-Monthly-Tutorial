package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.mvp.views.BaseView

interface HomeView : BaseView {
    fun displayConsultationRequest(consultationRequestVO: ConsultationRequestVO)
    fun displayRecentDoctorList (list : List<DoctorVO>)
    fun displaySpecialityList(list: List<SpecialitiesVO>)
}