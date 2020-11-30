package mk.padc.share.data.models

import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.networks.FirebaseApi

interface DoctorModel
{
    var mFirebaseApi : FirebaseApi

    fun saveNewDoctorRecord(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}