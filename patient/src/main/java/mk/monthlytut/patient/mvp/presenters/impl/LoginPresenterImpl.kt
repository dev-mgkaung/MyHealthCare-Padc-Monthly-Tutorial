package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.LoginPresenter
import mk.monthlytut.patient.mvp.views.LoginView
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    private  val mModel : PatientModel =PatientModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onTapLogin(context: Context,email: String, password: String,  owner: LifecycleOwner) {

        if(email.isEmpty() || password.isEmpty()){
            mView.showError("Please enter all the fields")
        } else {
            mAuthenticatioModel.login(email, password, onSuccess = {
                mModel.getPatientByEmail(email,onSuccess = {}, onError = {})
                mModel.getPatientByEmailFromDB(email)
                    .observe(owner, Observer { patient ->
                        patient?.let {
                            mView?.navigateToHomeScreen(patient) }
                    })

            }, onFailure = {
                mView.showError(it)
            })
        }
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }


}