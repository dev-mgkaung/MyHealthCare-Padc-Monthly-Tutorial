package mk.padc.share.data.models

import mk.padc.share.data.vos.PatientVO

interface PatientModel
{
    fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}