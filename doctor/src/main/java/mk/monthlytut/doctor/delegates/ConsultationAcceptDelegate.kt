package mk.monthlytut.doctor.delegates

import mk.padc.share.data.vos.ConsultationRequestVO

interface ConsultationAcceptDelegate {
    fun onTapMedicalRecord(consultationRequestVO: ConsultationRequestVO)
    fun onTapPrescription(consultationRequestVO: ConsultationRequestVO)
    fun onTapSendMessage(consultationRequestVO: ConsultationRequestVO)
    fun onTapDoctorComment(consultationRequestVO: ConsultationRequestVO)
}