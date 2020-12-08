package mk.monthlytut.doctor.mvp.presenters

import android.content.Context
import mk.monthlytut.doctor.mvp.views.RegisterView
import mk.padc.share.mvp.presenters.BasePresenter

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, username: String, email: String, password: String, token : String, speciality_name: String,
    speciality_type: String, phone: String, degree: String, biography : String)
    fun navigateToLoginScreen()
}