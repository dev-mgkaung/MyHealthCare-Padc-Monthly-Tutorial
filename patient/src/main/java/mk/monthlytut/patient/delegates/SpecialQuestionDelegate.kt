package mk.monthlytut.patient.delegates

import mk.padc.share.data.vos.QuestionAnswerVO

interface SpecialQuestionDelegate {
    fun onAnswerChange(position: Int, questionanswervo : QuestionAnswerVO)
}