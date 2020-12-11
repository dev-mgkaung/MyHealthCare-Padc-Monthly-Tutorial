package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ChatRoomPresenterImpl : ChatRoomPresenter, AbstractBasePresenter<ChatView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl

    override fun onUiReadyConstulation(consultationChatId: String, owner: LifecycleOwner) {

        doctorModel.getConsultationChat(consultationChatId,onSuccess = {}, onError = {})

        doctorModel.getConsultationChatFromDB(consultationChatId)
                .observe(owner, Observer { data ->
                    data?.let {
                       mView?.displayPatientInfo(data)
                            }
                })
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