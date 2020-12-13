package mk.monthlytut.patient.mvp.views

import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.views.BaseView


interface PrescriptionInfoView : BaseView {
    fun displayPrescriptionList(prescription_list : List<PrescriptionVO>)
}