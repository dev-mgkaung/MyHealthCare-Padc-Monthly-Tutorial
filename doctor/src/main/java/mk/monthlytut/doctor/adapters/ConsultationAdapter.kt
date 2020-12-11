package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ConsultationDelegate
import mk.monthlytut.doctor.views.viewholders.ConsultationViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ConsultationChatVO

class ConsultationAdapter(private val mDelegate: ConsultationDelegate) :
        BaseRecyclerAdapter<ConsultationViewHolder, ConsultationChatVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_consultation_accept, parent, false)
        return ConsultationViewHolder(view, mDelegate)

    }
}
