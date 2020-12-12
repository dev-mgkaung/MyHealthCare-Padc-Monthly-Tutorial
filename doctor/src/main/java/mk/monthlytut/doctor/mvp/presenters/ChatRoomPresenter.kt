package mk.monthlytut.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.monthlytut.doctor.delegates.QuestionAnswerDelegate
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.padc.share.mvp.presenters.BasePresenter

interface ChatRoomPresenter : BasePresenter<ChatView>, ChatRoomDelegate, QuestionAnswerDelegate
{
    fun onUiReadyConstulation( consultationChatId : String ,  owner: LifecycleOwner)
    fun addTextMessage(message : String, consultationChatId: String ,senderId : String ,senderPhoto: String, senderName : String, owner: LifecycleOwner)
}