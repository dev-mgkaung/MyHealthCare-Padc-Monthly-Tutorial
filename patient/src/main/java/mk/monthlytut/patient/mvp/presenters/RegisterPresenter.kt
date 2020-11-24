package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.presenters.BasePresenter


interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, patientVO: PatientVO, password: String)
    fun navigateToLoginScreen()
}