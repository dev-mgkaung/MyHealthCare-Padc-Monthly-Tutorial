
package mk.padc.share.networks

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import mk.padc.share.data.vos.*
import mk.padc.share.utils.doctors
import mk.padc.share.utils.patients
import mk.padc.share.utils.specialities
import java.io.ByteArrayOutputStream
import java.util.*

object ColudFirebaseDatabaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference


    override fun uploadPhotoToFirebaseStorage(
        image: Bitmap,
        onSuccess: (photoUrl: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            onFailure("Update Profile Failed")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d(ContentValues.TAG, "User profile updated.")
        }


        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val imageUrl = task.result?.toString()
            imageUrl?.let { onSuccess(it) }
        }
    }

    override fun addOrUpdatePatientData(patientVO: PatientVO,onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection(patients)
            .document(patientVO?.email.toString())
            .set(patientVO)
            .addOnSuccessListener { Log.d("Success", "Successfully") }
            .addOnFailureListener { Log.d("Failure", "Failed ") }

    }

    override fun addOrUpdateDoctorData(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection(doctors)
            .document(doctorVO?.email.toString())
            .set(doctorVO)
            .addOnSuccessListener { Log.d("Success", "Successfully") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun registerNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: (doctorList: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun registerNewPatient(
        patientVO: PatientVO,
        onSuccess: (patientList: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection(specialities)
            .get()
            .addOnSuccessListener { result ->
                val specialities: MutableList<SpecialitiesVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<SpecialitiesVO>(Data, SpecialitiesVO::class.java)
                    specialities.add(docsData)
                }
                onSuccess(specialities)
            }

//        db.collection(specialities)
//            .addSnapshotListener { value, error ->
//                error?.let {
//                    onFailure(it.message ?: "Please check connection")
//                } ?: run {
//                    val specialities: MutableList<SpecialitiesVO> = arrayListOf()
//
//                    val result = value?.documents ?: arrayListOf()
//
//                    for (document in result) {
//                        val hashmap = document.data
//                        hashmap?.put("id", document.id.toString())
//                        val Data = Gson().toJson(hashmap)
//                        val docsData = Gson().fromJson<SpecialitiesVO>(Data, SpecialitiesVO::class.java)
//                        specialities.add(docsData)
//                    }
//                    onSuccess(specialities)
//                }
//            }


    }


    override fun getBroadConsultationRequest(
        onSuccess: (consulationRequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun startConsultation(
        onSuccess: (consulation: List<String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun finishConsultation(
        onSuccess: (consulation: List<String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun finishConsultation(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun preScriptionMedicine(
        onSuccess: (consulation: List<String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerVO: GeneralQuestionVO,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun sendDirectRequest(
        questionAnswerVO: GeneralQuestionVO,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun checkoutMedicine(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getRecentlyConsultatedDoctor(
        onSuccess: (doctor: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getConsultationChat(
        onSuccess: (List<ConsultationChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }


    override fun getAllCheckMessage(
        documentId: String,
        onSuccess: (List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(
        documentId: String,
        messageVO: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun acceptRequest(
        doctor: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun preScribeMedicine(
        medicine: MedicineVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getGeneralQuestion(
        onSuccess: (List<GeneralQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}
