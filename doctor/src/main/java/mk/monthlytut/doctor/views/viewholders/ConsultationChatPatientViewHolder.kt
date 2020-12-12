package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_chat_patient.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.utils.ImageUtils

class ConsultationChatPatientViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolder(itemView) {
    override fun bindData(data: ChatMessageVO) {
        data.sendBy?.photo?.let{
            ImageUtils().showImage(itemView.patient_photo, data.sendBy?.photo.toString(), R.drawable.user)
        }
        itemView.patient_timestamp.text = data.sendBy?.arrived_time
        itemView.pateint_text_body.text = data.messageText
    }
}

