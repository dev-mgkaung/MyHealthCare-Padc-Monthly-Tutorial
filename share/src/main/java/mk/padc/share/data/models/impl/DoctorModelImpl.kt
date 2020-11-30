package mk.padc.share.data.models.impl

import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.networks.ColudFirebaseDatabaseApiImpl
import mk.padc.share.networks.FirebaseApi


object DoctorModelImpl : DoctorModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = ColudFirebaseDatabaseApiImpl

    override fun saveNewDoctorRecord(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

    }

}