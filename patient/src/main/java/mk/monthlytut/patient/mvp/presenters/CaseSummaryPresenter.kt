package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.delegates.QuestionAnswerDelegate
import mk.monthlytut.patient.delegates.SpecialQuestionDelegate
import mk.monthlytut.patient.mvp.views.CaseSummaryView
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.mvp.presenters.BasePresenter


interface CaseSummaryPresenter : BasePresenter<CaseSummaryView> , SpecialQuestionDelegate , QuestionAnswerDelegate {
    fun onUiReadyforSpecialQuestion(context: Context,speciality: String, owner: LifecycleOwner)
    fun onUiReadyforGeneralQuestion(context: Context,email: String, owner: LifecycleOwner)
    fun onTapSendBroadCast(context: Context,speciality: String,  questionAnswerList: List<QuestionAnswerVO>,patientVO: PatientVO, doctorVO: DoctorVO)
    fun navigateToNextScreen()
}