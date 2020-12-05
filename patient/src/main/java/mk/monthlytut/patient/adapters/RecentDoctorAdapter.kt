package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.RecentDoctorViewItemActionDelegate
import mk.monthlytut.patient.views.viewholders.RecentDoctorViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.DoctorVO

class RecentDoctorAdapter(private val mDelegate: RecentDoctorViewItemActionDelegate) :
    BaseRecyclerAdapter<RecentDoctorViewHolder, DoctorVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentDoctorViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recent_doctor, parent, false)
        return RecentDoctorViewHolder(view, mDelegate)

    }
}
