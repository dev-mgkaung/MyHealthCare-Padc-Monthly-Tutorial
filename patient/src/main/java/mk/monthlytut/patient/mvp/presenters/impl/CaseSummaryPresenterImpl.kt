package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.CaseSummaryPresenter
import mk.monthlytut.patient.mvp.views.CaseSummaryView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class CaseSummaryPresenterImpl : CaseSummaryPresenter, AbstractBasePresenter<CaseSummaryView>() {

    private val patientModel : PatientModel = PatientModelImpl

    override fun onUiReadyforSpecialQuestion(
        context: Context,
        speciality: String,
        owner: LifecycleOwner
    ) {
        patientModel.getSpecialQuestionBySpeciality(speciality, onSuccess = {} , onError = {})

        patientModel.getSpecialQuestionBySpecialityFromDB()
            .observe(owner, Observer {
                mView?.displaySpecialQuestions(it)
            })
    }

    override fun onUiReadyforGeneralQuestion(
        context: Context,
        email: String,
        owner: LifecycleOwner
    ) {
       patientModel.getPatientByEmailFromDB(email)
           .observe(owner, Observer { patient ->
               if(patient.blood_type.isBlank())
               {
                   mView?.displayOnceGeneralQuestion()
               } else
               {
                   mView?.displayAlwaysGeneralQuestion()
               }
           })
    }

    override fun onTapSendBroadCast(
        context: Context,
        speciality: String,
        specialQuestionVO: SpecialQuestionVO,
        patientVO: PatientVO
    ) {

    }

    override fun navigateToNextScreen() {}


    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onAnswerChange(position: Int, questionAnswerVO: QuestionAnswerVO) {
     mView?.replaceQuestionAnswerList(position,questionAnswerVO)
    }


}