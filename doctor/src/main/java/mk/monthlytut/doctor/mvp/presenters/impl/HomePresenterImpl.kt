package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


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

        doctorModel.getConsultationByDoctorId(SessionManager.doctor_id.toString(), onSuccess = {}, onError = {})

        doctorModel.getConsultationByDoctorIdFromDB(SessionManager.doctor_id.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayConsultationList(data) }
                })

        doctorModel.getConsultedPatient(SessionManager.doctor_id.toString(),onSuccess = {}, onError = {})

        doctorModel.getConsultedPatientFromDB()
            .observe(owner, Observer { data ->
                data?.let {
                    mView?.displayConsultedPatient(data) }
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
        mView?.displayPostPoneChooserDialog(consultationRequestVO)
    }

    override fun onTapPostponeTime(postponeTime : String, consultationRequestVO: ConsultationRequestVO) {
        acceptRequest("postpone $postponeTime", consultationRequestVO)
    }

    override fun onTapAccept(consultationRequestVO: ConsultationRequestVO) {
        acceptRequest("accept", consultationRequestVO)
    }

    override fun onTapMedicalRecord(data: ConsultationChatVO) {
       mView?.displayPatientInfoDialog(data)
    }

    override fun onTapPrescription(data: ConsultationChatVO) {
        mView?.displayPrescriptionDialog(data.id, data.patient_info?.name.toString(), data.start_consultation_date.toString())
    }


    override fun onTapSendMessage(data: ConsultationChatVO) {
        mView?.nextPageChatRoom(data.id)
    }

    override fun onTapDoctorComment(data: ConsultationChatVO) {

        doctorModel.getConsultationChatFromDB(data.id)
                .observe(mOwner, Observer { data ->
                    data?.let {
                        mView?.displayMedicalCommentDialog(it)
                    }
                })
    }

    private fun acceptRequest(status: String,  consultationRequestVO: ConsultationRequestVO) {
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
        doctorModel.acceptRequest(
                status,
                consultationRequestVO.id,
                consultationRequestVO.case_summary,
                consultationRequestVO.patient_info,
                doctorVo, onSuccess = {}, onFailure = {})

        Log.d("consulation",consultationRequestVO.id)
        mView?.nextPagePatientInfo(consultationRequestVO.id)
    }


}