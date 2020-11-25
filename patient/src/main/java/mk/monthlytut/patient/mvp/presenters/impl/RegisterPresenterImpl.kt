package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.mvp.presenters.RegisterPresenter
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.MyCareModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.MyCareModelImpl
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val myCareModel : MyCareModel = MyCareModelImpl
    private val patientModel : PatientModel = PatientModelImpl

    override fun onTapRegister(context: Context, patientVO: PatientVO, password: String) {
        if(patientVO.email.isEmpty() || password.isEmpty() || patientVO.name.isEmpty()){
            mView.showError("Please enter all fields")
        } else {

            mAuthenticationModel.register(patientVO.name, patientVO.email, password, onSuccess = {

                myCareModel.registerNewPatient(patientVO,  onSuccess = {
                    patientModel?.saveNewPatientRecord(patientVO, onSuccess = {}, onError = {})
                    mView.navigateToToLoginScreen()
                },onFailure = {} )

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