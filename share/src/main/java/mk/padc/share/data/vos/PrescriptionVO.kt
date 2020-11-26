package mk.padc.share.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class PrescriptionVO(
    var ps_id: String= "",
    var total_price: Int = 0,
    var prescriptionMedicineVO: ArrayList<PrescriptionMedicineVO> = arrayListOf()
)

class PrescriptionMedicineVO(
    var name: String= "",
    var price: Int = 0,
    var count: Int = 0
)