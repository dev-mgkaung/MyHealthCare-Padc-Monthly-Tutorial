package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.mvp.views.BaseView

interface ChatHistoryView : BaseView {
   fun displayChatHistoryList( list: List<ConsultationChatVO>)
}