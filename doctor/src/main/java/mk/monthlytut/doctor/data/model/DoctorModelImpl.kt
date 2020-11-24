package mk.monthlytut.doctor.data.model

import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.vos.DoctorVO


object DoctorModelImpl : DoctorModel , BaseModel() {

    override fun saveNewDoctorRecord(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mTheDB.doctorDao().insertDoctorData(doctorVO)
    }

}