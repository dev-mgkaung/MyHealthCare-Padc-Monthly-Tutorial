package mk.monthlytut.doctor.mvp.presenters

import android.content.Context
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.presenters.BasePresenter


interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, doctorVO: DoctorVO, password: String)
    fun navigateToLoginScreen()
}