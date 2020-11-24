package mk.padc.share.data.models

import android.graphics.Bitmap
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.networks.FirebaseApi

interface MyCareModel {

    var mFirebaseApi : FirebaseApi

    //var mFirebaseRemoteConfigManager : FirebaseRemoteConfigManager

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun getDoctors(onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getPatients(onSuccess: (List<PatientVO>) -> Unit, onFaiure: (String) -> Unit)

}