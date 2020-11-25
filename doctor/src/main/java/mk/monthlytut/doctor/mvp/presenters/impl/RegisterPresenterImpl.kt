package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.mvp.presenters.RegisterPresenter
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.MyCareModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.models.impl.MyCareModelImpl
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val myCareModel : MyCareModel = MyCareModelImpl
    private val doctorModel : DoctorModel = DoctorModelImpl

    override fun onTapRegister(context: Context, doctorVO: DoctorVO, password: String) {
        if(doctorVO.email.isEmpty() || password.isEmpty() || doctorVO.name.isEmpty()){
            mView.showError("Please enter all fields")
        } else {

            mAuthenticationModel.register(doctorVO.name, doctorVO.email, password, onSuccess = {

                myCareModel.registerNewDoctor(doctorVO,  onSuccess = {
                    doctorModel?.saveNewDoctorRecord(doctorVO, onSuccess = {}, onError = {})
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