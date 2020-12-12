package mk.monthlytut.doctor.views.viewholders

import android.view.View
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.views.viewholders.BaseViewHolder


class ConsultationChatDoctorViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
    BaseViewHolder<ChatMessageVO>(itemView) {

    override fun bindData(data: ChatMessageVO) {


    }
}