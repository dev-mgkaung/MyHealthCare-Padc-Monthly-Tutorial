package mk.monthlytut.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.padc.share.mvp.presenters.BasePresenter

interface ChatRoomPresenter : BasePresenter<ChatView>, ChatRoomDelegate
{
    fun onUiReadyConstulation( consultationChatId : String ,  owner: LifecycleOwner)
}