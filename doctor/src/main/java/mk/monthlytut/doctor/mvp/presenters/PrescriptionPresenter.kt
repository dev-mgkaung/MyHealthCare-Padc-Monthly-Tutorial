package mk.monthlytut.doctor.mvp.presenters

import mk.monthlytut.doctor.delegates.MedicalDelegate
import mk.monthlytut.doctor.mvp.views.PrescriptionView
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.mvp.presenters.BasePresenter

interface PrescriptionPresenter : BasePresenter<PrescriptionView>, MedicalDelegate {
    fun onUiReadyForPrescription ( speciality : String)
  fun onTapFinishConsulation(list : List<PrescriptionVO> )
}