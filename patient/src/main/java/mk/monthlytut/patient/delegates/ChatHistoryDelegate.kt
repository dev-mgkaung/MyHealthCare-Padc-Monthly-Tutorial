package mk.monthlytut.patient.delegates

import mk.padc.share.data.vos.ConsultationChatVO


interface ChatHistoryDelegate {
    fun onTapSendMessage(consultationChatVO: ConsultationChatVO)
    fun onTapPrescription(consultationChatVO: ConsultationChatVO)
}