package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.google.firebase.messaging.FirebaseMessaging
import mk.monthlytut.patient.mvp.presenters.RegisterPresenter
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import java.util.*


class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val patientModel : PatientModel = PatientModelImpl

    override fun onTapRegister(context: Context, username: String , email : String , password: String, token : String) {
        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            mView.showError("Please enter all fields")
        } else {

            val patientVO = PatientVO(
                id = UUID.randomUUID().toString(),
                name = username,
                email = email,
                device_id = token
            )

            mAuthenticationModel.register(username,  email, password, onSuccess = {
                mView.navigateToToLoginScreen()
                patientModel.registerNewPatient(patientVO,  onSuccess = {},onFailure = {} )
            }, onFailure = {
                mView.showError(it)
            })

        }
    }

    override fun navigateToLoginScreen() {
        mView.navigateToToLoginScreen()
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}