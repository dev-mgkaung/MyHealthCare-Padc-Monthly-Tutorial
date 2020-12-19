package mk.monthlytut.patient.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.delegates.ChatRoomDelegate
import mk.monthlytut.patient.delegates.QuestionAnswerDelegate
import mk.monthlytut.patient.mvp.views.ChatView
import mk.monthlytut.patient.views.viewpods.PrescriptionViewPod
import mk.padc.share.mvp.presenters.BasePresenter


interface ChatRoomPresenter : BasePresenter<ChatView>, ChatRoomDelegate, QuestionAnswerDelegate, PrescriptionViewPod.Delegate
{
    fun onUiReadyConstulation( consultationChatId : String ,  owner: LifecycleOwner)
    fun addTextMessage(message : String, consultationChatId: String ,senderId : String ,senderPhoto: String, senderName : String, owner: LifecycleOwner)
    fun onCallPrescription(consultationChatId: String,owner: LifecycleOwner)
}