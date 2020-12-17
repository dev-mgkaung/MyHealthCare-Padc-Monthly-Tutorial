package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.CheckoutDelegate
import mk.monthlytut.patient.views.viewholders.MedicineListViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.PrescriptionVO

class CheckoutAdpater(private val mDelegate: CheckoutDelegate) :
        BaseRecyclerAdapter<MedicineListViewHolder, PrescriptionVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineListViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.medicine_listitem, parent, false)
        return MedicineListViewHolder(view, mDelegate)

    }
}
