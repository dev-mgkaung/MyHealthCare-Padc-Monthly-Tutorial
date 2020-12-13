package mk.monthlytut.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.delegates.MedicalRecordDelegate
import mk.monthlytut.doctor.mvp.views.MedicalRecordView
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.mvp.presenters.BasePresenter

interface MedicalRecordPresenter : BasePresenter<MedicalRecordView>, MedicalRecordDelegate {
  fun onTapSaveMedicalRecord(consultationChatVO: ConsultationChatVO ,owner: LifecycleOwner)
}