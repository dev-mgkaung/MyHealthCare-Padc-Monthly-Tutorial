package mk.padc.share.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.vos.*
import mk.padc.share.networks.FirebaseApi

interface PatientModel
{
    var mFirebaseApi : FirebaseApi

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun registerNewPatient(patientVO: PatientVO ,onSuccess: (patientVO: PatientVO) -> Unit, onFailure: (String) -> Unit)

    fun getSpecialities(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialitiesFromDB(): LiveData<List<SpecialitiesVO>>

    fun getRecentDoctors(
        patientId : String,
        onSuccess: (List<RecentDoctorVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getRecentDoctorsFromDB(): LiveData<List<RecentDoctorVO>>

    fun getSpecialQuestionBySpeciality(
        speciality : String,
        onSuccess: (List<SpecialQuestionVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialQuestionBySpecialityFromDB() : LiveData<List<SpecialQuestionVO>>

    fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)
}