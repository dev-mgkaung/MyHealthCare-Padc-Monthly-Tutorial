package mk.monthlytut.patient.mvp.views

import mk.padc.share.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen()
    fun navigateToRegisterScreen()
}