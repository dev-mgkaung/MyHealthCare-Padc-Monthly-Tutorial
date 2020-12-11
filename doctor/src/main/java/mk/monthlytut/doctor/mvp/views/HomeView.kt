package mk.monthlytut.doctor.mvp.views

import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.ConsultedPatientVO
import mk.padc.share.mvp.views.BaseView

interface HomeView : BaseView {
  fun displayConsultationRequests(list: List<ConsultationRequestVO>)
  fun displayConsultationAcceptList(list: List<ConsultationRequestVO>)
  fun displayConsultedPatient(list : List<ConsultedPatientVO>)
  fun nextPage(data : ConsultationRequestVO)
  fun displayPostPoneChooserDialog(consultationRequestVO: ConsultationRequestVO)
}