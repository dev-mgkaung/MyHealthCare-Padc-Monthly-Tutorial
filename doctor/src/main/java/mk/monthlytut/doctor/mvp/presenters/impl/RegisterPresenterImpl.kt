package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.mvp.presenters.RegisterPresenter
import mk.monthlytut.doctor.mvp.views.RegisterView
import mk.padc.share.R
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import java.util.*


class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mModel : DoctorModel = DoctorModelImpl

    override fun onTapRegister(
        context: Context,
        username: String,
        email: String,
        password: String,
        token: String,
        speciality_name: String,
        speciality_type: String,
        phone: String,
        degree: String,
        biography: String,
        address: String, experience: String, dateofBirth :String, gender : String
    ) {

        if(email.isEmpty() || password.isEmpty() || username.isEmpty() || speciality_name.isEmpty() || speciality_type.isEmpty() ||
                phone.isEmpty() || degree.isEmpty() || biography.isEmpty()){
            mView.showError("သင့်အချက်အလက် အားလုံး ပြည့်စုံအောင် ဖြည့်စွက်ပေးရန် လိုနေပါသေး သည်")
        } else {

            val doctorVO = DoctorVO(
                id = UUID.randomUUID().toString(),
                name = username,
                email = email,
                device_id = token,
                biography = biography,
                degree = degree,
                phone = phone,
                speciality = speciality_type,
                specialityname = speciality_name,
                    experience = experience,
                    dateofBirth = dateofBirth,
                    gender = gender,
                    address = address
            )

            mAuthenticationModel.register(username,  email, password, onSuccess = {
                mView.navigateToToLoginScreen()
                mModel.registerNewDoctor(doctorVO = doctorVO,  onSuccess = {},onFailure = {} )
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