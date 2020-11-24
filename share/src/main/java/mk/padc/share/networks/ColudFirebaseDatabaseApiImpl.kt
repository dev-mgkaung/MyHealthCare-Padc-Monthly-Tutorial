
package mk.padc.share.networks

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.utils.doctors
import mk.padc.share.utils.patients
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

    override fun getDoctorList(
        onSuccess: (podcast: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("doctors")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val doctorList: MutableList<DoctorVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

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

    }

    override fun getPatientList(
        onSuccess: (patientList: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patients")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val patientList: MutableList<PatientVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id)
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<PatientVO>(Data, PatientVO::class.java)
                        patientList.add(docsData)
                    }
                    onSuccess(patientList)
                }
            }
    }

}
