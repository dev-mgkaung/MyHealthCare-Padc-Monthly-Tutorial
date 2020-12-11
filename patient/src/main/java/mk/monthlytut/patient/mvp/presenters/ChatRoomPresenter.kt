package mk.monthlytut.patient.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.delegates.ChatRoomDelegate
import mk.monthlytut.patient.mvp.views.ChatView
import mk.padc.share.mvp.presenters.BasePresenter


interface ChatRoomPresenter : BasePresenter<ChatView>, ChatRoomDelegate
{
    fun onUiReadyConstulation( consultationChatId : String ,  owner: LifecycleOwner)
}