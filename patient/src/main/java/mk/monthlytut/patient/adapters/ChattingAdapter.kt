package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ChatRoomDelegate
import mk.monthlytut.patient.views.viewholders.ConsultationChatViewHolder
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