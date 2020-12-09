package mk.monthlytut.doctor.delegates

import mk.padc.share.data.vos.ConsultationRequestVO

interface ConsultationRequestDelegate {
    fun onTapNext(consultationRequestVO: ConsultationRequestVO)
    fun onTapSkip(consultationRequestVO: ConsultationRequestVO)
    fun onTapPostpone(consultationRequestVO: ConsultationRequestVO)
    fun onTapAccept(consultationRequestVO: ConsultationRequestVO)
}