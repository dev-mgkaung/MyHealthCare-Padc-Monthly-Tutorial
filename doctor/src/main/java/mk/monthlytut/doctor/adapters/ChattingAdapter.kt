package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.monthlytut.doctor.views.viewholders.ConsultationChatViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ConsultationChatVO

class ChattingAdapter(private val mDelegate: ChatRoomDelegate) :
    BaseRecyclerAdapter<ConsultationChatViewHolder, ConsultationChatVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationChatViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_chat_patient, parent, false)
        return ConsultationChatViewHolder(view, mDelegate)

    }
}