package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ConsultationAcceptDelegate
import mk.monthlytut.patient.views.viewholders.ConsultationAcceptViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ConsultationRequestVO


class ConsultationAdapter(private val mDelegate: ConsultationAcceptDelegate) :
        BaseRecyclerAdapter<ConsultationAcceptViewHolder, ConsultationRequestVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationAcceptViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewpod_consulation_request, parent, false)
        return ConsultationAcceptViewHolder(view, mDelegate)

    }
}
