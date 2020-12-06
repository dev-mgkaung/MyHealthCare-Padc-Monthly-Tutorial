package mk.monthlytut.patient.mvp.views


import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.mvp.views.BaseView

interface CaseSummaryView : BaseView {
    fun displaySpecialQuestions(list: List<SpecialQuestionVO>)
}