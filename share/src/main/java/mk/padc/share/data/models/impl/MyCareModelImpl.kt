package mk.padc.share.data.models.impl


import android.graphics.Bitmap
import mk.padc.share.data.models.MyCareModel
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.networks.ColudFirebaseDatabaseApiImpl
import mk.padc.share.networks.FirebaseApi

object MyCareModelImpl : MyCareModel {

    override var mFirebaseApi: FirebaseApi = ColudFirebaseDatabaseApiImpl


    override fun uploadPhotoToFirebaseStorage(image: Bitmap , onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.uploadPhotoToFirebaseStorage(image ,onSuccess,onFailure)
    }

    override fun getDoctors(onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getDoctorList(onSuccess, onFaiure)
    }

    override fun getPatients(
        onSuccess: (List<PatientVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientList(onSuccess, onFaiure)
    }
}