package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.CaseSummaryPresenter
import mk.monthlytut.patient.mvp.views.CaseSummaryView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.utils.DateUtils
import mk.padc.share.utils.prepareNotificationForPatient

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
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
    ) {

       speciality?.let{

           patientModel.sendBroadCastConsultationRequest(speciality,questionAnswerList,patientVO,doctorVO,DateUtils().getCurrentDate(),
           onSuccess = {} , onFailure = {})

            val notiData = prepareNotificationForPatient(context,"/topics/$speciality",patientVO)
            patientModel.sendBroadcastToDoctor( notiData ,onSuccess= {
                Log.d("onsuccess", it.success.toString())
            }, onFailure = {
                Log.d("notionFailure", it)
            })
       }

      }

    override fun navigateToNextScreen() {}

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onAnswerChange(position: Int, questionAnswerVO: QuestionAnswerVO) {
        mView?.replaceQuestionAnswerList(position,questionAnswerVO)
    }


}