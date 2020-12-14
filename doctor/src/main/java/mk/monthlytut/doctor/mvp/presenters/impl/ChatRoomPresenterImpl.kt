package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.data.vos.SendBy
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.utils.DateUtils
import java.util.*

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

        doctorModel.getChatMessage(consultationChatId, onSuccess = {}, onError = {})

        doctorModel.getAllChatMessageFromDB()
            .observe(owner, Observer { data ->
            data?.let {
                mView?.displayChatMessageList(data)
            }
        })



        doctorModel.getPrescription(consultationChatId, onSuccess = {}, onError = {})

        doctorModel.getPrescriptionFromDB()
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayPrescriptionViewPod(it)
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
       doctorModel.sendChatMessage(chatMessage,consultationChatId,onSuccess = {} , onError = {})
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

    override fun onTapSendTextMessage(message: String) {

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