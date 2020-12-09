package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ConsultationRequestDelegate
import mk.monthlytut.doctor.views.viewholders.ConsultationRequestViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ConsultationRequestVO

class ConsultationRequestAdapter(private val mDelegate: ConsultationRequestDelegate) :
        BaseRecyclerAdapter<ConsultationRequestViewHolder, ConsultationRequestVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationRequestViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_consultation_request, parent, false)
        return ConsultationRequestViewHolder(view, mDelegate)

    }
}
