package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen(patientVO: PatientVO)
    fun navigateToRegisterScreen()
}