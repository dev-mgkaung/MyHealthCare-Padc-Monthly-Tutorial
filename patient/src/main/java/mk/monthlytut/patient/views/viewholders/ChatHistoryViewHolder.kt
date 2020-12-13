package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_chathistory.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ChatHistoryDelegate
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder


class ChatHistoryViewHolder(itemView: View, private val mDelegate: ChatHistoryDelegate) :
        BaseViewHolder<ConsultationChatVO>(itemView) {


    override fun bindData(data: ConsultationChatVO) {

        data?.let {
            itemView.startconservationdate.text =data.start_consultation_date
            ImageUtils().showImage(itemView.doctorphoto, data.doctor_info?.photo.toString(), R.drawable.doctor_thumbnail)
            itemView.chat_doctor_name.text = data.doctor_info?.name
            itemView.chat_doctor_specialityname.text = data.doctor_info?.specialityname
        }

        itemView.sendtextlayout.setOnClickListener {
          mDelegate.onTapSendMessage(data)
        }

        itemView.prescriptionlayout.setOnClickListener {
            mDelegate.onTapPrescription(data)
        }


    }
}