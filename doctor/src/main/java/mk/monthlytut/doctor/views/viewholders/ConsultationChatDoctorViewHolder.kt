package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_chat_doctor.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.utils.ImageUtils


class ConsultationChatDoctorViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolder(itemView) {
    override fun bindData(data: ChatMessageVO) {
        ImageUtils().showImage(itemView.img_doctor_photo, data.sendBy?.photo.toString(), R.drawable.user)
        itemView.txt_time_stamp.text = data.sendAt + "Am"
        itemView.text_message_body.text = data.messageText
    }
}

