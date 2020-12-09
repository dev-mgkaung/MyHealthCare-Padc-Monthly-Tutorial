package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ConsultationAcceptDelegate
import mk.monthlytut.doctor.views.viewholders.ConsultationAcceptViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ConsultationRequestVO

class ConsultationAcceptAdapter(private val mDelegate: ConsultationAcceptDelegate) :
        BaseRecyclerAdapter<ConsultationAcceptViewHolder, ConsultationRequestVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationAcceptViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_consultation_accept, parent, false)
        return ConsultationAcceptViewHolder(view, mDelegate)

    }
}
