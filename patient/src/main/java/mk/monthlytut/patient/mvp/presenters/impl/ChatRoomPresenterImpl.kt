package mk.monthlytut.patient.mvp.presenters.impl


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.patient.mvp.views.ChatView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ChatRoomPresenterImpl : ChatRoomPresenter, AbstractBasePresenter<ChatView>() {

    private val patientModel: PatientModel = PatientModelImpl

    override fun onUiReadyConstulation(consultationChatId: String, owner: LifecycleOwner) {
        patientModel.getConsultationChat(consultationChatId,onSuccess = {}, onError = {})

        patientModel.getConsultationChatFromDB(consultationChatId)
            .observe(owner, Observer { data ->
                data?.let {
                    mView?.displayPatientInfo(data)
                }
            })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onTapSendTextMessage() {

    }

    override fun onTapAttachImage() {

    }

    override fun onTapQuestionTemplate() {

    }

    override fun onTapPrescription() {

    }

    override fun onTapDoctorComment() {
        TODO("Not yet implemented")
    }


}