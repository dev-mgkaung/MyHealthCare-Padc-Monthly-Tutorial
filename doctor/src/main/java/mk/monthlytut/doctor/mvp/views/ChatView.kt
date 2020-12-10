package mk.monthlytut.doctor.mvp.views

import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.mvp.views.BaseView


interface ChatView : BaseView {
    fun displayPatientInfo(consultationChatVO: ConsultationChatVO)
}