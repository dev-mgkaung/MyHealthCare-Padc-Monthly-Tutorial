package mk.monthlytut.patient.data.model

import mk.padc.share.data.vos.PatientVO

interface PatientModel
{
    fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}