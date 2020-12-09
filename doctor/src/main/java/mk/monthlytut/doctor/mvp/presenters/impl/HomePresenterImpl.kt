package mk.monthlytut.doctor.mvp.presenters.impl


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.ConsultationRequestVO
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
                        mView?.displayConsultationRequests(consultationRequest) }
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
        TODO("Not yet implemented")
    }

    override fun onTapAccept(consultationRequestVO: ConsultationRequestVO) {
        TODO("Not yet implemented")
    }

    override fun onTapMedicalRecord(consultationRequestVO: ConsultationRequestVO) {
        TODO("Not yet implemented")
    }

    override fun onTapPrescription(consultationRequestVO: ConsultationRequestVO) {
        TODO("Not yet implemented")
    }

    override fun onTapSendMessage(consultationRequestVO: ConsultationRequestVO) {
        TODO("Not yet implemented")
    }

    override fun onTapDoctorComment(consultationRequestVO: ConsultationRequestVO) {
        TODO("Not yet implemented")
    }


}