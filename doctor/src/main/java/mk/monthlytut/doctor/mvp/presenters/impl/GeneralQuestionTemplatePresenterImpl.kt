package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.GeneralQuestionTemplatePresenter
import mk.monthlytut.doctor.mvp.views.GeneralQuestionTemplateView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.GeneralQuestionTemplateVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class GeneralQuestionTemplatePresenterImpl : GeneralQuestionTemplatePresenter, AbstractBasePresenter<GeneralQuestionTemplateView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        doctorModel.getGeneralQuestionTemplate(onSuccess = {}, onError = {})
        doctorModel.getGeneralQuestionTemplateFromDB()
                .observe(owner, Observer {
                    mView?.displayGeneralQuestions(it)
                })
    }

    override fun onTapOneQuestion(generalQuestionTemplateVO: GeneralQuestionTemplateVO) {
        mView?.navigateToToChatRoom(generalQuestionTemplateVO.question.toString())
    }
}