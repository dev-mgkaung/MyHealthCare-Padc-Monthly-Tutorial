package mk.padc.share.data.models

import androidx.lifecycle.LiveData
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.networks.FirebaseApi

interface PatientModel
{
    var mFirebaseApi : FirebaseApi

    fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialities(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialitiesFromDB(): LiveData<List<SpecialitiesVO>>

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