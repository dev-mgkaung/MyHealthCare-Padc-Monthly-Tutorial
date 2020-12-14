package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.ChatPresenter
import mk.monthlytut.patient.mvp.views.ChatHistoryView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class ChatPresenterImpl : ChatPresenter, AbstractBasePresenter<ChatHistoryView>() {

    private val patientModel: PatientModel = PatientModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

        patientModel.getConsultationChatByPatientId(SessionManager.patient_id.toString(),onSuccess = {}, onError = {})

        patientModel.getConsultationChatByPatientIdFromDB(SessionManager.patient_id.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayChatHistoryList(data)
                    }
                })

    }

    override fun onTapSendMessage(consultationChatVO: ConsultationChatVO) {
        mView?.nextPageToChatRoom(consultationChatVO.id)
    }

    override fun onTapPrescription(consultationChatVO: ConsultationChatVO) {
        mView?.showPrescriptionDialog(consultationChatVO.finish_consultation_status,consultationChatVO.id, consultationChatVO.patient_info?.name.toString(), consultationChatVO.start_consultation_date.toString())
    }
}