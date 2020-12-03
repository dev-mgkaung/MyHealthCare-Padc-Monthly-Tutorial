
package mk.padc.share.networks

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import mk.padc.share.data.vos.*
import mk.padc.share.utils.*
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


    override fun registerNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(doctors)
                .document(doctorVO.id)
                .set(doctorVO)
                .addOnSuccessListener { Log.d("Success", "Successfully") }
                .addOnFailureListener { Log.d("Failure", "Failed ") }
    }

    override fun registerNewPatient(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(patients)
                .document(patientVO.id)
                .set(patientVO)
                .addOnSuccessListener { Log.d("Success", "Successfully") }
                .addOnFailureListener { Log.d("Failure", "Failed ") }
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

    }

    override fun getSpecialQuestionsBySpeciality(
        speciality: String,
        onSuccess: (specialQuestionList: List<SpecialQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
            db.collection("$specialities/$speciality/$special_questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val specialQuestionList: MutableList<SpecialQuestionVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<SpecialQuestionVO>(Data, SpecialQuestionVO::class.java)
                        specialQuestionList.add(docsData)
                    }
                    onSuccess(specialQuestionList)
                }
            }
    }

    override fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()
        val consultationRequestMap = hashMapOf(
            "case_summary" to questionAnswerList,
            "id" to id,
            "patient_info" to patientVO,
            "speciality" to speciality)

        db.collection(consultation_request)
            .document(id)
            .set(consultationRequestMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }

    }


    override fun getBroadConsultationRequest(
        onSuccess: (consulationRequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun startConsultation(
        dateTime: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()

        val consultationChatMap = hashMapOf(
            "case_summary" to questionAnswerList,
            "id" to id,
            "patient_info" to patientVO,
            "doctor_info" to doctorVO)

        db.collection(consultation_chat)
            .document(id)
            .set(consultationChatMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }


        db.collection("$patients/${patientVO.id}/$recent_doctors")
            .document(doctorVO.id)
            .set(doctorVO)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }

       for(item in questionAnswerList) {
           db.collection("$patients/${patientVO.id}/$general_questions")
               .document(item.id)
               .set(item)
               .addOnSuccessListener { Log.d("Success", "Successfully ") }
               .addOnFailureListener { Log.d("Failure", "Failed") }
       }
    }

    override fun sendMessage(
        consulationId: String,
        messageVO: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("$consultation_chat/$consulationId/$chat_message")
            .document(messageVO.id)
            .set(messageVO)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun preScribeMedicine(
        consulationId: String,
        prescriptionVO: PrescriptionVO,
        routineVO: RoutineVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()
        val prescriptionVOMap = hashMapOf(
            "medicine_name" to prescriptionVO.medicine,
            "id" to id,
            "routine" to routineVO)

        db.collection("$consultation_chat/$consulationId/$prescription")
            .document(id)
            .set(prescriptionVOMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
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

    override fun sendDirectRequest(
        questionAnswerVO: QuestionAnswerVO,
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
        patientId: String,
        onSuccess: (doctorList: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$patients/$patientId/$recent_doctors")
            .get()
            .addOnSuccessListener { result ->
                val doctorList: MutableList<DoctorVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<DoctorVO>(Data, DoctorVO::class.java)
                    doctorList.add(docsData)
                }
                onSuccess(doctorList)
            }
    }



    override fun getConsultationChat(
        patientId: String,
        onSuccess: (List<ConsultationChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$consultation_chat")
            .whereEqualTo(patient_id,patientId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<ConsultationChatVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<ConsultationChatVO>(Data, ConsultationChatVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list)
                }
            }

        // get chat Message
        // get prescription
    }


    override fun getAllCheckMessage(
        documentId: String,
        onSuccess: (List<ChatMessageVO>) -> Unit,
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



    override fun getGeneralQuestion(
        onSuccess: (List<QuestionAnswerVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}
