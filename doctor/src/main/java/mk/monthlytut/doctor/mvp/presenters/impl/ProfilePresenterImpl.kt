package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.ProfilePresenter
import mk.monthlytut.doctor.mvp.views.ProfileView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ProfilePresenterImpl : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mModel : DoctorModel = DoctorModelImpl

    override fun onUiReadyForProfile(context: Context, owner: LifecycleOwner) {
        mModel.getDoctorByEmail(SessionManager.doctor_email.toString(),onSuccess = {}, onError = {})
        mModel.getDoctorByEmailFromDB(SessionManager.doctor_email.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayDocotrData(data) }
                })
    }

    override fun updateUserData(bitmap: Bitmap,
                                specialitname : String,
                                speciality : String,
                                phone : String, degree : String,
                                bigraphy : String, address : String,
                                experience : String, dateofbirth : String,
                                gender: String)
    {
        mModel.uploadPhotoToFirebaseStorage(bitmap,
                onSuccess = {
                    mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})

                    mView?.hideProgressDialog()

                    var doctorVO = DoctorVO(
                            id= SessionManager.doctor_id.toString(),
                            device_id = SessionManager.doctor_device_id.toString(),
                            name = SessionManager.doctor_name.toString(),
                            email = SessionManager.doctor_email.toString(),
                            photo = it,
                            speciality = speciality,
                            specialityname = specialitname,
                            phone = phone,
                            dateofBirth = dateofbirth,
                             degree = degree,
                            biography = bigraphy,
                            address = address,
                            experience = experience,
                            gender = gender
                    )
                    mModel.addDoctorInfo(doctorVO,onSuccess = {}, onError = {})
                },
                onFailure = {
                    mView?.showError("Profile Update Failed")
                })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mModel.getDoctorByEmailFromDB(SessionManager.doctor_email.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayDocotrData(data) }
                })
    }
}

