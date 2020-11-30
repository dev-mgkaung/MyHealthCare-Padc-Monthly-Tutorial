package mk.padc.share.data.models.impl

import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.MyCareModelImpl.mFirebaseApi
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.networks.ColudFirebaseDatabaseApiImpl
import mk.padc.share.networks.FirebaseApi


object PatientModelImpl : PatientModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = ColudFirebaseDatabaseApiImpl

    override fun getSpecialities(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    ) {

        mFirebaseApi.getSpecialities(onSuccess = {
            mTheDB.specialityDao().deleteSpecialities()
            mTheDB.specialityDao().insertSpecialities(it)
        }, onFailure =
        { onError(it) })

    }

    override fun getSpecialitiesFromDB(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    ) {
          mTheDB.specialityDao().getAllSpecialitiesData()
    }

    override fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mTheDB.patientDao().insertNewPatient(patientVO)
    }



}