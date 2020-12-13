package mk.monthlytut.doctor.mvp.views

import mk.padc.share.data.vos.GeneralQuestionTemplateVO
import mk.padc.share.mvp.views.BaseView

interface GeneralQuestionTemplateView : BaseView {
    fun displayGeneralQuestions(list : List<GeneralQuestionTemplateVO>)
    fun navigateToToChatRoom(questions : String)
}