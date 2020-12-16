package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.PatientInfoPresenter
import mk.monthlytut.doctor.mvp.views.PatientInfoView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.utils.DateUtils

class PatientInfoPresenterImpl : PatientInfoPresenter, AbstractBasePresenter<PatientInfoView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl
    lateinit var mOwner: LifecycleOwner

    override fun onTapStartConsultation( consultationRequestVO: ConsultationRequestVO) {

      doctorModel.startConsultation(consultationRequestVO.id,
          DateUtils().getCurrentDate(), consultationRequestVO.case_summary,
          consultationRequestVO.patient_info, consultationRequestVO.doctor_info,
          onSuccess = {} , onFailure = {})

        doctorModel.getConsultationByConsulationRequestId(consultationRequestVO.id, onSuccess = {}, onError = {})

        doctorModel.getConsultationByConsulationRequestIdFromDB(consultationRequestVO.id)
                .observe(mOwner, Observer {
                    it?.let{
                      //  doctorModel.sendNotificationToPatient()
                        mView?.nextPageToChat(it.consultation_id.toString())
                    }

                })

    }

    override fun onUiReadyConstulation(id: String , owner: LifecycleOwner) {
        mOwner =owner
        doctorModel.getConsultationByConsulationRequestIdFromDB(id)
            .observe(owner, Observer {
                it?.let{
                    mView?.displayPatientInfo(it)
                }

            })

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}