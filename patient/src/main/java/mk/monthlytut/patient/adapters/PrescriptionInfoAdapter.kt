package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.PrescriptionInfoDelegate
import mk.monthlytut.patient.views.viewholders.PrescriptionInfoViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.PrescriptionVO

class PrescriptionInfoAdapter(private val mDelegate: PrescriptionInfoDelegate) :
        BaseRecyclerAdapter<PrescriptionInfoViewHolder, PrescriptionVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionInfoViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_prescription_info, parent, false)
        return PrescriptionInfoViewHolder(view, mDelegate)

    }
}
