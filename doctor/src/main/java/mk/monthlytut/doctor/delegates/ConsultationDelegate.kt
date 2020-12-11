package mk.monthlytut.doctor.delegates

import mk.padc.share.data.vos.ConsultationChatVO

interface ConsultationDelegate {
    fun onTapMedicalRecord(data: ConsultationChatVO)
    fun onTapPrescription(data: ConsultationChatVO)
    fun onTapSendMessage(data: ConsultationChatVO)
    fun onTapDoctorComment(data: ConsultationChatVO)
}