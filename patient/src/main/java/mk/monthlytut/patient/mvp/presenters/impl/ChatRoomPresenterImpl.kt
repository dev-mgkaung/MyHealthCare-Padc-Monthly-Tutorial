package mk.monthlytut.patient.mvp.presenters.impl


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.patient.mvp.views.ChatView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ChatRoomPresenterImpl : ChatRoomPresenter, AbstractBasePresenter<ChatView>() {

    private val patientModel: PatientModel = PatientModelImpl

    override fun onUiReadyConstulation(consultationChatId: String, owner: LifecycleOwner) {

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onTapSendTextMessage() {
        TODO("Not yet implemented")
    }

    override fun onTapAttachImage() {
        TODO("Not yet implemented")
    }

    override fun onTapQuestionTemplate() {
        TODO("Not yet implemented")
    }

    override fun onTapPrescription() {
        TODO("Not yet implemented")
    }

    override fun onTapDoctorComment() {
        TODO("Not yet implemented")
    }


}