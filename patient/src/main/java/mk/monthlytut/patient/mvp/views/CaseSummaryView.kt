package mk.monthlytut.patient.mvp.views

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.mvp.views.BaseView

interface CaseSummaryView : BaseView {
    fun onUiReady(context: Context, speciality: String, owner: LifecycleOwner)
    fun displayGeneralQuestions (list : List<QuestionAnswerVO>)
    fun displaySpecialQuestions(list: List<SpecialQuestionVO>)
}