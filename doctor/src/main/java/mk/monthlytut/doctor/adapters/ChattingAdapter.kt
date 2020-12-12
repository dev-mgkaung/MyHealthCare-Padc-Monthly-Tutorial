package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.monthlytut.doctor.views.viewholders.ConsultationChatDoctorViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ChatMessageVO

class ChattingAdapter(private val mDelegate: ChatRoomDelegate) :
    BaseRecyclerAdapter<ConsultationChatDoctorViewHolder, ChatMessageVO>() {

    internal val VIEW_TYPE_PATIENT =1
    internal val VIEW_TYPE_DOCTOR =2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationChatDoctorViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_chat_patient, parent, false)
        return ConsultationChatDoctorViewHolder(view, mDelegate)

    }
}