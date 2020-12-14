package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.RecentDoctorVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.mvp.views.BaseView

interface HomeView : BaseView {
    fun displayConsultationRequest(consultationRequestVO: List<ConsultationRequestVO>)
    fun displayRecentDoctorList (list : List<RecentDoctorVO>)
    fun displaySpecialityList(list: List<SpecialitiesVO>)
    fun nextPageToCaseSummary (specialitiesVO: SpecialitiesVO)
    fun nextPageToCaseSummaryFromRecentDoctor( doctorVO: RecentDoctorVO)
    fun nextPageToChatRoom(consulation_chat_id : String,consultationRequestVO: ConsultationRequestVO)
}