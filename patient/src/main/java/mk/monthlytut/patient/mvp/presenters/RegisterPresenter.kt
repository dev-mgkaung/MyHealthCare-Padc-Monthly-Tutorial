package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.mvp.presenters.BasePresenter

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, username: String, email: String, password: String, token : String)
    fun navigateToLoginScreen()
}