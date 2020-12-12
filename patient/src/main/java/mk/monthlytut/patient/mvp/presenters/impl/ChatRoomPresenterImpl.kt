package mk.monthlytut.patient.mvp.presenters.impl


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.patient.mvp.views.ChatView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.data.vos.SendBy
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.utils.DateUtils
import java.util.*

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

        patientModel.getChatMessage(consultationChatId, onSuccess = {}, onError = {})

        patientModel.getAllChatMessageFromDB()
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayChatMessageList(data)
                    }
                })
    }

    override fun addTextMessage(
        message: String,
        consultationChatId: String,
        type : String,
        senderPhoto : String,
        senderName: String,
        owner: LifecycleOwner
    ) {
        val id = UUID.randomUUID().toString()
        var sendBy = SendBy(
            photo = senderPhoto,
            name = senderName,
            arrived_time = DateUtils().getCurrentHourMinAMPM()
        )
        var chatMessage = ChatMessageVO(id =id, message, "", DateUtils().getCurrentDateTime(), sendBy, type
        )
        patientModel.sendChatMessage(chatMessage,consultationChatId,onSuccess = {} , onError = {})
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