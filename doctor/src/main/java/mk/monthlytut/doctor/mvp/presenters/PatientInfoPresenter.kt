package mk.monthlytut.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.delegates.QuestionAnswerDelegate
import mk.monthlytut.doctor.mvp.views.PatientInfoView
import mk.padc.share.mvp.presenters.BasePresenter

interface PatientInfoPresenter : BasePresenter<PatientInfoView> , QuestionAnswerDelegate {
    fun onTapStartConsultation( consultation_chat_id : String)
    fun onUiReadyConstulation( consulationRequestId : String ,  owner: LifecycleOwner)
}