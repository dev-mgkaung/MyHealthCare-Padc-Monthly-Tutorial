package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.mvp.views.LoginView
import mk.padc.share.mvp.presenters.BasePresenter

interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(context: Context, email: String, password: String, owner: LifecycleOwner)
    fun onTapRegister()
}