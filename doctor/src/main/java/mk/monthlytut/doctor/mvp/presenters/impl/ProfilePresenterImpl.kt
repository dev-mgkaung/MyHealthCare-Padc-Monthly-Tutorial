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

    override fun updateUserData(bitmap: Bitmap, blood_type: String, dateofbirth: String, height: String, comment: String, phone: String) {
        mModel.uploadPhotoToFirebaseStorage(bitmap,
                onSuccess = {
                    mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})

                    mView?.hideProgressDialog()

//                    var patientVo = DoctorVO(
//                            id= SessionManager.patient_id.toString(),
//                            device_id = SessionManager.patient_device_id.toString(),
//                            name = SessionManager.patient_name.toString(),
//                            email = SessionManager.patient_email.toString(),
//                            photo = it,
//                            blood_type = blood_type,
//                            blood_pressure =SessionManager.patient_bloodPressure.toString(),
//                            dateOfBirth = dateofbirth,
//                            weight = SessionManager.patient_weight.toString(),
//                            height = height,
//                            comment = comment,
//                            phone = phone
//                    )
//                    mModel.addPatientInfo(patientVo,onSuccess = {}, onError = {})
                },
                onFailure = {
                    mView?.showError("Profile Update Failed")
                })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}

