package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.gson.Gson
import mk.monthlytut.patient.mvp.views.ProfileView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ProfilePresenterImpl : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val patientModel : PatientModel = PatientModelImpl

    override fun onUiReadyForAccountFragment(context: Context, owner: LifecycleOwner) {
        patientModel.getPatientByEmail(SessionManager.patient_email.toString(),onSuccess = {}, onError = {})
        patientModel.getPatientByEmailFromDB(SessionManager.patient_email.toString())
            .observe(owner, Observer { patient ->
                patient?.let {
                    SessionManager.addPatientInfo(patient)
                    mView?.displayPatientData(patient) }
            })
    }


    override fun updateUserData(
        bitmap: Bitmap,
        blood_type: String,
        dateofbirth: String,
        height: String,
        comment: String,
        phone: String,
        address: String
    ) {

        patientModel.uploadPhotoToFirebaseStorage(bitmap,
            onSuccess = {
                mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})

                mView?.hideProgressDialog()
                val gson = Gson()
                var addressList = gson.fromJson(SessionManager.patient_address, Array<String>::class.java).toMutableList()
                var patientVo = PatientVO(
                    id= SessionManager.patient_id.toString(),
                    device_id = SessionManager.patient_device_id.toString(),
                    name = SessionManager.patient_name.toString(),
                    email = SessionManager.patient_email.toString(),
                    photo = it,
                    blood_type = blood_type,
                    blood_pressure =SessionManager.patient_bloodPressure.toString(),
                    dateOfBirth = dateofbirth,
                    weight = SessionManager.patient_weight.toString(),
                    height = height,
                    comment = comment,
                    phone = phone,
                        perment_address = address,
                        address = addressList as ArrayList<String>
                )
                patientModel.addPatientInfo(patientVo,onSuccess = {}, onError = {})
            },
            onFailure = {
                mView?.showError("Profile Update Failed")
            })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        patientModel.getPatientByEmailFromDB(SessionManager.patient_email.toString())
                .observe(owner, Observer { patient ->
                    patient?.let {
                        SessionManager.addPatientInfo(patient)
                        mView?.displayPatientData(patient) }
                })
    }
}

