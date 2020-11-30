package mk.padc.share.data.models

import mk.padc.share.data.vos.PatientVO
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

    fun getSpecialitiesFromDB(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    )
}