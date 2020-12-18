package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.views.BaseView

interface CheckOutView : BaseView {
    fun displayPrescription(list: List<PrescriptionVO>)
    fun displayShippingAddress (list : List<String>)
    fun displayConfirmDialog(list: List<PrescriptionVO>, total_price: String, address: String)
}