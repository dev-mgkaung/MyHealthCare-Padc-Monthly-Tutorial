package mk.monthlytut.doctor.mvp.presenters

import mk.monthlytut.doctor.delegates.PrescriptionInfoDelegate
import mk.monthlytut.doctor.mvp.views.PrescriptionInfoView
import mk.padc.share.mvp.presenters.BasePresenter

interface PrescriptionInfoPresenter : BasePresenter<PrescriptionInfoView>, PrescriptionInfoDelegate {
    fun onUiReadyForPrescription ( consulation_chat_id : String)
}