package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ChatHistoryDelegate
import mk.monthlytut.patient.views.viewholders.ChatHistoryViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ConsultationChatVO


class ChatAdapter(private val mDelegate: ChatHistoryDelegate) :
        BaseRecyclerAdapter<ChatHistoryViewHolder, ConsultationChatVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_chathistory, parent, false)
        return ChatHistoryViewHolder(view, mDelegate)

    }
}
