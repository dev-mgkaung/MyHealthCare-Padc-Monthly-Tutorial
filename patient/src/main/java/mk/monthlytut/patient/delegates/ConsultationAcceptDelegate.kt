package mk.monthlytut.patient.delegates

import mk.padc.share.data.vos.ConsultationRequestVO

interface ConsultationAcceptDelegate {
    fun onTapStarted(consultationChatId : String, consultationRequestVO: ConsultationRequestVO)
}