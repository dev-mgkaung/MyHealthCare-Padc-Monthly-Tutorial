package mk.monthlytut.patient.data.model

import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.vos.PatientVO


object PatientModelImpl : PatientModel , BaseModel() {

    override fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mTheDB.patientDao().insertPatientData(patientVO)
    }

}