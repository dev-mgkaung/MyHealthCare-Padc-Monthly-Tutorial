package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.views.BaseView

interface ChatView : BaseView {
    fun displayPatientInfo(consultationChatVO: ConsultationChatVO)
    fun displayChatMessageList(list : List<ChatMessageVO>)
    fun displayPrescriptionViewPod(prescription_list : List<PrescriptionVO>)
    fun nextPageToCheckout(chatId: String)
}