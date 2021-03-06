package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.LoginPresenter
import mk.monthlytut.doctor.mvp.views.LoginView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    private val mModel : DoctorModel = DoctorModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onTapLogin(context: Context,email: String, password: String,  owner: LifecycleOwner) {
        mModel.getDoctorByEmail(email,onSuccess = {}, onError = {})
        if(email.isEmpty() || password.isEmpty()){
            mView.showError("Please enter all the fields")
        } else {
            mAuthenticatioModel.login(email, password, onSuccess = {
                mModel.getDoctorByEmailFromDB(email)
                    .observe(owner, Observer { doctor ->
                        doctor?.let {

                            var mDoctor = doctor
                            mDoctor.device_id = SessionManager.doctor_device_id
                            mModel.addDoctorInfo(mDoctor,onSuccess = {}, onError = {})

                            mView?.navigateToMainScreen(doctor)

                        }
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