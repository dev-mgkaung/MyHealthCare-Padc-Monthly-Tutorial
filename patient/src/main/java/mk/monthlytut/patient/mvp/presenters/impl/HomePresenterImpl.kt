package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.HomePresenter
import mk.monthlytut.patient.mvp.views.HomeView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.RecentDoctorVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {

    private val patientModel : PatientModel = PatientModelImpl

    override fun onTapSpeciality(context: Context, specialitiesVO: SpecialitiesVO) {

    }

    override fun onTapSpeciality(specialitiesVO: SpecialitiesVO) {
        mView?.nextPageToCaseSummary(specialitiesVO)
    }

    override fun navigateToNextScreen() {

    }

    override fun statusUpdateForCompleteType(
        context: Context,
        cosultation_chat_id: String,
        consultationRequestVO: ConsultationRequestVO
    ) {
        patientModel.joinedChatRoomPatient(cosultation_chat_id,consultationRequestVO,
            onSuccess = {}, onError = {})
    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {

        patientModel.getSpecialities(onSuccess = {}, onError = {})

        patientModel.getSpecialitiesFromDB()
            .observe(owner, Observer {
                mView?.displaySpecialityList(it)
            })

        patientModel.getRecentDoctors(SessionManager.patient_id.toString() , onSuccess = {}, onError = {})

        patientModel.getRecentDoctorsFromDB()
            .observe(owner, Observer {
                mView?.displayRecentDoctorList(it)
            })

        patientModel.getConsultationAccepts(SessionManager.patient_id.toString(), onSuccess = {}, onError = {})

        patientModel.getConsultationAcceptsFromDB()
                .observe(owner, Observer {
                   var data =it.filter{
                       it.consultation_id.toString().isNotEmpty()
                   }
                    mView?.displayConsultationRequest(data)
                })
    }

    override fun onTapRecentDoctor(doctorVO: RecentDoctorVO) {
        mView.nextPageToCaseSummaryFromRecentDoctor(doctorVO)
    }



    override fun onTapStarted(consultationChatId: String, consultationRequestVO: ConsultationRequestVO) {
     mView?.nextPageToChatRoom(consultationChatId , consultationRequestVO)
    }


}