package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.views.BaseView

interface ProfileView : BaseView {
    fun displayPatientData( patientVO: PatientVO)
    fun hideProgressDialog()
}