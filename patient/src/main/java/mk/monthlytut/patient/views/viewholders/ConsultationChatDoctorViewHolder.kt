package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_chat_doctor.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ChatRoomDelegate
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.utils.ImageUtils



class ConsultationChatDoctorViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolder(itemView) {
    override fun bindData(data: ChatMessageVO) {
        data.sendBy?.photo?.let{
            ImageUtils().showImage(itemView.doctor_photo, data.sendBy?.photo.toString(), R.drawable.user)
        }
        itemView.text_timestamp.text = data.sendBy?.arrived_time
        itemView.text_message_body.text = data.messageText
    }
}

