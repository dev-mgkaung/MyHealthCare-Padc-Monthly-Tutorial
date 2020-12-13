package mk.monthlytut.doctor.mvp.views

import mk.padc.share.data.vos.MedicineVO
import mk.padc.share.mvp.views.BaseView

interface PrescriptionView : BaseView {
    fun displayMedicineList(list: List<MedicineVO>)
    fun displayRoutinechooseDialog(medicineVO: MedicineVO)
    fun removeMedicine(medicineVO: MedicineVO)
    fun finishConsulation()
}