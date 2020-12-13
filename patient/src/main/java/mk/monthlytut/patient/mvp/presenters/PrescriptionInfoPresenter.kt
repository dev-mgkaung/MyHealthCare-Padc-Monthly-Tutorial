package mk.monthlytut.patient.mvp.presenters

import mk.monthlytut.patient.delegates.PrescriptionInfoDelegate
import mk.monthlytut.patient.mvp.views.PrescriptionInfoView
import mk.padc.share.mvp.presenters.BasePresenter

interface PrescriptionInfoPresenter : BasePresenter<PrescriptionInfoView>, PrescriptionInfoDelegate {
    fun onUiReadyForPrescription ( consulation_chat_id : String)
}