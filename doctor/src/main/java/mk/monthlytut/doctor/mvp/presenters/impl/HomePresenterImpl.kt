package mk.monthlytut.doctor.mvp.presenters.impl


import android.content.Context
import android.se.omapi.Session
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.utils.DateUtils


class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {

    private val doctorModel : DoctorModel = DoctorModelImpl
    lateinit var mOwner: LifecycleOwner

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner=  owner

        doctorModel.getBrodcastConsultationRequests(
                SessionManager.doctor_speciality.toString(),
                onSuccess = {},
                onError = {})

        doctorModel.getBrodcastConsultationRequestsFromDB(SessionManager.doctor_speciality.toString())
                .observe(owner, Observer { consultationRequest ->
                    consultationRequest?.let {
                      val data=  consultationRequest.filter {
                          it.status.toString() == "none"
                      }
                        mView?.displayConsultationRequests(data) }
                })

        doctorModel.getConsultationAcceptListFromDB(SessionManager.doctor_id.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayConsultationAcceptList(data) }
                })

    }

    override fun onTapNext(consultationRequestVO: ConsultationRequestVO) {
        doctorModel.deleteConsultationRequestById(consultationRequestVO.id)
                .observe(mOwner, Observer { consultationRequest ->
                    consultationRequest?.let {
                        mView?.displayConsultationRequests(consultationRequest) }
                })
    }

    override fun onTapSkip(consultationRequestVO: ConsultationRequestVO) {
        doctorModel.deleteConsultationRequestById(consultationRequestVO.id)
                .observe(mOwner, Observer { consultationRequest ->
                    consultationRequest?.let {
                        mView?.displayConsultationRequests(consultationRequest) }
                })
    }

    override fun onTapPostpone(consultationRequestVO: ConsultationRequestVO) {
        startConsulatationByDoctor("accept", "2 AM",consultationRequestVO)
    }

    override fun onTapAccept(consultationRequestVO: ConsultationRequestVO) {
        startConsulatationByDoctor("accept", "",consultationRequestVO)
    }

    override fun onTapMedicalRecord(consultationRequestVO: ConsultationRequestVO) {

    }

    override fun onTapPrescription(consultationRequestVO: ConsultationRequestVO) {

    }

    override fun onTapSendMessage(consultationRequestVO: ConsultationRequestVO) {

    }

    override fun onTapDoctorComment(consultationRequestVO: ConsultationRequestVO) {

    }

    private fun startConsulatationByDoctor(status: String, postPone: String, consultationRequestVO: ConsultationRequestVO) {
        var doctorVo = DoctorVO(
                id = SessionManager.doctor_id.toString(),
                device_id = SessionManager.doctor_device_id.toString(),
                name = SessionManager.doctor_name,
                phone = SessionManager.doctor_phone,
                degree = SessionManager.doctor_degree,
                email = SessionManager.doctor_email,
                biography = SessionManager.doctor_bigraphy,
                photo = SessionManager.doctor_photo,
                specialityname = SessionManager.doctor_specialityname,
                speciality = SessionManager.doctor_speciality
        )
        doctorModel.startConsultation(status, postPone,consultationRequestVO.id, DateUtils().getCurrentDate(), consultationRequestVO.case_summary,
                consultationRequestVO.patient_info, doctorVo, onSuccess = {
        }, onFailure = {})
    }


}