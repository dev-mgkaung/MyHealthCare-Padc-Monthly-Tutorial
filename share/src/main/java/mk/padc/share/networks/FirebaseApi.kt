package mk.padc.share.networks

import android.graphics.Bitmap
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO


interface FirebaseApi {

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun addOrUpdatePatientData(patientVO: PatientVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addOrUpdateDoctorData(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getDoctorList(
        onSuccess: (doctorList: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPatientList(
        onSuccess: (patientList: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    )

}