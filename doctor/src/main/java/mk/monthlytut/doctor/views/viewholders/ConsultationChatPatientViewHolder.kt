package mk.monthlytut.doctor.views.viewholders

import android.view.View
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.padc.share.data.vos.ChatMessageVO

class ConsultationChatPatientViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolder(itemView) {
    override fun bindData(data: ChatMessageVO) {

    }
}

