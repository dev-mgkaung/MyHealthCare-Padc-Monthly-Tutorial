package mk.padc.share.networks

import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO


interface FirebaseApi {

    fun getDoctorList(
        onSuccess: (doctorList: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPatientList(
        onSuccess: (patientList: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    )

}