
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

    override fun updatePatientData(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(patients)
            .document(patientVO.id)
            .set(patientVO)
            .addOnSuccessListener {
                Log.d("Success", "Successfully") }
            .addOnFailureListener {
                Log.d("Failure", "Failed ") }
    }

    override fun updateDoctorData(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection(doctors)
                .document(doctorVO.id)
                .set(doctorVO)
                .addOnSuccessListener {
                    Log.d("Success", "Successfully") }
                .addOnFailureListener {
                    Log.d("Failure", "Failed ") }
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
            .whereEqualTo("email", email)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<PatientVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<PatientVO>(Data, PatientVO::class.java)
                        list.add(docsData)
                    }
                    if(list.size >0) {
                        onSuccess(list[0])
                    }
                }
            }
    }

    override fun getDoctor(
        email: String,
        onSuccess: (doctorVO: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(doctors)
            .whereEqualTo("email", email)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<DoctorVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<DoctorVO>(Data, DoctorVO::class.java)
                        list.add(docsData)
                    }
                   list?.let{
                       if(list.size >0) {
                           onSuccess(list[0])
                       }
                   }
                }
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
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()
        val consultationRequestMap = hashMapOf(
            "case_summary" to questionAnswerList,
            "id" to id,
            "patient_info" to patientVO,
            "doctor_info" to doctorVO,
            "speciality" to speciality,
            "status" to "none")

        db.collection(consultation_request)
            .document(id)
            .set(consultationRequestMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }

        db.collection(patients)
            .document(patientVO.id)
            .set(patientVO)
            .addOnSuccessListener { Log.d("Success", "Successfully") }
            .addOnFailureListener { Log.d("Failure", "Failed ") }
    }


    override fun startConsultation(
        consulationId: String,
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
            "patient_id" to patientVO.id,
            "doctor_id" to doctorVO.id,
            "start_consultation_date" to DateUtils().getCurrentDate(),
            "patient_info" to patientVO,
            "medical_record" to "",
            "timestamp" to DateUtils().getCurrentDateTime(),
            "doctor_info" to doctorVO)

        db.collection(consultation_chat)
            .document(id)
            .set(consultationChatMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }


       for(item in questionAnswerList) {
           db.collection("$patients/${patientVO.id}/$general_questions")
               .document(item.id)
               .set(item)
               .addOnSuccessListener { Log.d("Success", "Successfully ") }
               .addOnFailureListener { Log.d("Failure", "Failed") }
       }


        val consultationRequestMap = hashMapOf(
            "status" to "accept",
            "patient_id" to patientVO.id,
            "doctor_info" to doctorVO,
            "speciality" to doctorVO.speciality,
            "patient_info" to patientVO,
            "case_summary" to questionAnswerList,
            "consultation_id" to  id
        )
        db.collection(consultation_request)
            .document(consulationId)
            .set(consultationRequestMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }


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

    override fun getBroadcastConsultationRequest(
        consulation_request_id : String,
        onSuccess: (consulationRequest: ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(consultation_request)
            .whereEqualTo("id", consulation_request_id)
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
                    list?.let{
                        if(list.size>0) {
                            onSuccess(list[0])
                        }
                    }

                }
            }
    }

    override fun getBroadcastConsultationRequestByPatient(patient_id : String, onSuccess: (consulationRequest: List<ConsultationRequestVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection(consultation_request)
                .whereEqualTo("patient_id", patient_id)
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
                        onSuccess(list)
                    }
                }
    }

    override fun getBroadcastConsultationRequestBySpeciality(speciality: String, onSuccess: (list: List<ConsultationRequestVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection(consultation_request)
                .whereEqualTo("speciality", speciality)
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
                        onSuccess(list)
                    }
                }
    }



    override fun checkoutMedicine(
        prescriptionList: List<PrescriptionVO>,
        deliveryAddressVO: String,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        total_price : String,
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
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val list: MutableList<RecentDoctorVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

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
            .orderBy("sendAt")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val list: MutableList<ChatMessageVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

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
    }

    override fun getPrescription( consulationId: String,
                                  onSuccess: (List<PrescriptionVO>) -> Unit,
                                  onFailure: (String) -> Unit) {

        db.collection("$consultation_chat/$consulationId/$prescription")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val list: MutableList<PrescriptionVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

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

    override fun startConsultationChatPatient(
        consulationChatId: String,
        consultationRequestVO: ConsultationRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val consultationRequestMap = hashMapOf(
            "status" to "complete",
            "doctor_id" to consultationRequestVO.doctor_info.id,
            "patient_id" to consultationRequestVO.patient_info.id,
            "doctor_info" to consultationRequestVO.doctor_info,
            "speciality" to consultationRequestVO.doctor_info.speciality,
            "patient_info" to consultationRequestVO.patient_info,
            "case_summary" to consultationRequestVO.case_summary,
            "consultation_id" to  consulationChatId
        )
        db.collection(consultation_request)
            .document(consultationRequestVO.id)
            .set(consultationRequestMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun getConsultedPatient(
        doctorId: String,
        onSuccess: (List<ConsultedPatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$doctors/$doctorId/$consulted_patient")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<ConsultedPatientVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<ConsultedPatientVO>(Data, ConsultedPatientVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list)
                }
            }
    }

    override fun addConsultedPatient(
        doctorId: String,
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()
        val consulatedPatientMap = hashMapOf(
            "id" to id,
            "patient_id" to patientId,
             "doctor_id" to doctorId
        )
        db.collection("$doctors/$doctorId/$consulted_patient")
            .document(id)
            .set(consulatedPatientMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
      }

    override fun getConsulationChatForDoctor(doctorId: String, onSuccess: (List<ConsultationChatVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("$consultation_chat")
                .whereEqualTo("doctor_id",doctorId)
                .orderBy("timestamp")
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

    override fun getConsulationChatById(id: String, onSuccess: (List<ConsultationChatVO>) -> Unit, onFailure: (String) -> Unit)
    {
        db.collection("$consultation_chat")
                .whereEqualTo("id",id)
                .orderBy("timestamp")
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

    override fun getConsulationChatByPatientId(patientid: String, onSuccess: (List<ConsultationChatVO>) -> Unit, onFailure: (String) -> Unit)
    {
        db.collection("$consultation_chat")
                .whereEqualTo("patient_id",patientid)
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

    override fun saveMedicalRecord(consultationChatVO: ConsultationChatVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection(consultation_chat)
                .document(consultationChatVO.id)
                .set(consultationChatVO)
                .addOnSuccessListener { Log.d("Success", "Successfully ") }
                .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun acceptRequest(
        status : String,
        consulationId: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patient : PatientVO,
        doctor: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {


        val consultationRequestMap = hashMapOf(
                "status" to status,
                "doctor_id" to doctor.id,
                "patient_id" to patient.id,
                "doctor_info" to doctor,
                "speciality" to doctor.speciality,
                "patient_info" to patient,
                "case_summary" to questionAnswerList,
                "consultation_id" to  "",
        )
        db.collection(consultation_request)
                .document(consulationId)
                .set(consultationRequestMap)
                .addOnSuccessListener { Log.d("Success", "Successfully ") }
                .addOnFailureListener { Log.d("Failure", "Failed") }

    }

    override fun sendDirectRequest(
        questionAnswerVO: QuestionAnswerVO,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun finishConsultation(consultationChatVO: ConsultationChatVO,prescriptionlist: List<PrescriptionVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    {

        //add recent doctor
        consultationChatVO.doctor_info?.let {

            db.collection("$patients/${consultationChatVO.patient_id}/$recent_doctors")
                    .document(consultationChatVO.doctor_id.toString())
                    .set(it)
                    .addOnSuccessListener { Log.d("Success", "Successfully ") }
                    .addOnFailureListener { Log.d("Failure", "Failed") }
        }


        // update finish conservation status
        val consultationChatMap = hashMapOf(
                "finish_consultation_status" to true,
                "id" to consultationChatVO.id,
                "patient_id" to consultationChatVO.patient_id,
                "doctor_id" to consultationChatVO.doctor_id,
                "case_summary" to consultationChatVO.case_summary,
                "patient_info" to consultationChatVO.patient_info,
                 "start_consultation_date" to consultationChatVO.start_consultation_date,
                "medical_record" to consultationChatVO.medical_record,
                "timestamp" to consultationChatVO.timestamp,
                "doctor_info" to consultationChatVO.doctor_info)

        db.collection("$consultation_chat")
                .document(consultationChatVO.id)
                .set(consultationChatMap)
                .addOnSuccessListener { Log.d("Success", "Successfully ") }
                .addOnFailureListener { Log.d("Failure", "Failed") }


        // add prescription

        for(item in prescriptionlist) {
            db.collection("$consultation_chat/${consultationChatVO.id}/$prescription")
                    .document(item.id)
                    .set(item)
                    .addOnSuccessListener { Log.d("Success", "Successfully ") }
                    .addOnFailureListener { Log.d("Failure", "Failed") }
        }

        val consultedid = UUID.randomUUID().toString()
        val consulatedPatientMap = hashMapOf(
                "id" to consultedid,
                "patient_id" to consultationChatVO.patient_id,
                "doctor_id" to consultationChatVO.doctor_id
        )
        db.collection("$doctors/${consultationChatVO.doctor_id}/$consulted_patient")
                .document(consultedid)
                .set(consulatedPatientMap)
                .addOnSuccessListener { Log.d("Success", "Successfully ") }
                .addOnFailureListener { Log.d("Failure", "Failed") }
    }

}
