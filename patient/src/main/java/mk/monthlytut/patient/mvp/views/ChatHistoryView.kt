package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.mvp.views.BaseView

interface ChatHistoryView : BaseView {
   fun displayChatHistoryList( list: List<ConsultationChatVO>)
   fun nextPageToChatRoom(consulationchatId : String)
   fun showPrescriptionDialog(finish_consulation:Boolean ,consulationchatId : String, patient_name: String,start_conservation_date : String)
}