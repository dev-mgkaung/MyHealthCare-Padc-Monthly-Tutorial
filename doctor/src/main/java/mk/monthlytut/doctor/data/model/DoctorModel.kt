package mk.monthlytut.doctor.data.model

import mk.padc.share.data.vos.DoctorVO

interface DoctorModel
{
    fun saveNewDoctorRecord(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}