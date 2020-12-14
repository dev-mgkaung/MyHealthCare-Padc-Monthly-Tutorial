package mk.monthlytut.doctor.mvp.views

import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.ConsultedPatientVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.views.BaseView

interface HomeView : BaseView {
  fun displayConsultationRequests(list: List<ConsultationRequestVO>)
  fun displayConsultationList(list: List<ConsultationChatVO>)
  fun displayConsultedPatient(list : List<ConsultedPatientVO>)
  fun nextPageChatRoom(consultation_chat_id : String)
  fun nextPagePatientInfo(consultation_request_id : String)
  fun displayPostPoneChooserDialog(consultationRequestVO: ConsultationRequestVO)
  fun displayPatientInfoDialog(consultationChatVO: ConsultationChatVO)
  fun displayPrescriptionDialog(consultation_chat_id: String, patient_name : String, start_conservation_date : String)
  fun displayMedicalCommentDialog(consultationChatVO: ConsultationChatVO)
  fun displayPostponseProcessSuccess()
}