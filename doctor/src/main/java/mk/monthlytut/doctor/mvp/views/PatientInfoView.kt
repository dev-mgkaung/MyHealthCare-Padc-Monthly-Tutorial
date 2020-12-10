package mk.monthlytut.doctor.mvp.views

import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.mvp.views.BaseView

interface PatientInfoView : BaseView {
    fun displayPatientInfo(consultationRequestVO: ConsultationRequestVO)
}