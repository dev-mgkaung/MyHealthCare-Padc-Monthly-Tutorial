package mk.monthlytut.doctor.delegates

import mk.padc.share.data.vos.GeneralQuestionTemplateVO


interface QuestionTemplateDelegate {
    fun onTapOneQuestion(generalQuestionTemplateVO: GeneralQuestionTemplateVO)
}