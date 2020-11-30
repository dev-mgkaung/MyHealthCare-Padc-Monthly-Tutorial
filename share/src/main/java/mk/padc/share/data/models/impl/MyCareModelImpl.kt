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


    override fun registerNewPatient(patientVO: PatientVO,onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addOrUpdatePatientData(patientVO,onSuccess,onFailure)
    }

    override fun registerNewDoctor(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit){
        mFirebaseApi.addOrUpdateDoctorData(doctorVO,onSuccess,onFailure)
    }


}