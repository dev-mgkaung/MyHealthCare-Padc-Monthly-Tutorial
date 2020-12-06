
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

    override fun getPatient(email: String, onSuccess: (patientVO: PatientVO) -> Unit, onFailure: (String) -> Unit) {
        db.collection(patients)
            .whereEqualTo("email",email)
            .get()
            .addOnSuccessListener { result ->
                val list: MutableList<PatientVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<PatientVO>(Data, PatientVO::class.java)
                    list.add(docsData)
                }
                onSuccess(list[0])
            }
    }

    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection(specialities)
            .get()
            .addOnSuccessListener { result ->
                val list: MutableList<SpecialitiesVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<SpecialitiesVO>(Data, SpecialitiesVO::class.java)
                    list.add(docsData)
                }
                onSuccess(list)
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
            "finish_consultation_status" to false,
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
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()
        val prescriptionVOMap = hashMapOf(
            "medicine_name" to prescriptionVO.medicine,
            "id" to id)

        db.collection("$consultation_chat/$consulationId/$prescription")
            .document(id)
            .set(prescriptionVOMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun getBroadConsultationRequest(
        consulation_request_id : String,
        onSuccess: (consulationRequest: ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$consultation_request/$consulation_request_id")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<ConsultationRequestVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<ConsultationRequestVO>(Data, ConsultationRequestVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list[0])
                }
            }
    }

    override fun finishConsultation(consulationChatId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    {

        //update consultation status

        val consultationChatMap = hashMapOf(
            "finish_consultation_status" to true,
            "id" to consulationChatId)

        db.collection("$consultation_chat")
            .document(consulationChatId)
            .set(consultationChatMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }



    override fun checkoutMedicine(
        prescriptionList: List<PrescriptionVO>,
        deliveryAddressVO: DeliveryAddressVO,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        total_price : Int,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val deliveryRoutineVO = DeliveryRoutineVO("1" , DateUtils().getDaysAgo(2).toString())

        val id = UUID.randomUUID().toString()
        val checkoutVOMap = hashMapOf(
            "id" to id,
            "patient_info" to patientVO,
            "doctor_info" to doctorVO,
            "delivery_address" to deliveryAddressVO,
            "delivery_routine" to deliveryRoutineVO,
            "prescription" to prescriptionList,
            "total_price" to total_price)

        db.collection("$checkout")
            .document(id)
            .set(checkoutVOMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }


    override fun getRecentlyConsultatedDoctor(
        patientId: String,
        onSuccess: (doctorList: List<RecentDoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$patients/$patientId/$recent_doctors")
            .get()
            .addOnSuccessListener { result ->
                val list: MutableList<RecentDoctorVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<RecentDoctorVO>(Data, RecentDoctorVO::class.java)
                    list.add(docsData)
                }
                onSuccess(list)
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
    }


    override fun getAllChatMessage(
        consulationId: String,
        onSuccess: (List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$consultation_chat/$consulationId/$chat_message")
            .get()
            .addOnSuccessListener { result ->
                val list: MutableList<ChatMessageVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<ChatMessageVO>(Data, ChatMessageVO::class.java)
                    list.add(docsData)
                }
                onSuccess(list)
            }
    }

    override fun getPrescription( consulationId: String,
                                  onSuccess: (List<PrescriptionVO>) -> Unit,
                                  onFailure: (String) -> Unit) {
        db.collection("$consultation_chat/$consulationId/$prescription")
            .get()
            .addOnSuccessListener { result ->
                val list: MutableList<PrescriptionVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id.toString())
                    val Data = Gson().toJson(hashmap)
                    val docsData = Gson().fromJson<PrescriptionVO>(Data, PrescriptionVO::class.java)
                    list.add(docsData)
                }
                onSuccess(list)
            }
    }





    override fun getGeneralQuestion(
        onSuccess: (List<GeneralQuestionTemplateVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$general_question_template")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<GeneralQuestionTemplateVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<GeneralQuestionTemplateVO>(Data, GeneralQuestionTemplateVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list)
                }
            }
    }

    override fun getAllMedicine(
        speciality: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$specialities/$speciality/$medicine")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<MedicineVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<MedicineVO>(Data, MedicineVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list)
                }
            }
    }


    override fun acceptRequest(
        doctor: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
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

}
