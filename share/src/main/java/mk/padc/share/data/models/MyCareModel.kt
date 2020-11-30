package mk.padc.share.data.models

import android.graphics.Bitmap
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.networks.FirebaseApi

interface MyCareModel {

    var mFirebaseApi : FirebaseApi

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun registerNewPatient(patientVO: PatientVO ,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun registerNewDoctor(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)

}