package mk.monthlytut.doctor.mvp.presenters

import mk.monthlytut.doctor.delegates.QuestionTemplateDelegate
import mk.monthlytut.doctor.mvp.views.GeneralQuestionTemplateView
import mk.padc.share.mvp.presenters.BasePresenter


interface GeneralQuestionTemplatePresenter : BasePresenter<GeneralQuestionTemplateView> , QuestionTemplateDelegate {

}