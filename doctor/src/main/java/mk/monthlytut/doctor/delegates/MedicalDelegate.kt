package mk.monthlytut.doctor.delegates

import mk.padc.share.data.vos.MedicineVO


interface MedicalDelegate {
    fun onTapSelectMedicine(medicineVO: MedicineVO)
    fun onTapRemoveMedicine(medicineVO: MedicineVO)
}