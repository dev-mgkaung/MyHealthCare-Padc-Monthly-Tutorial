package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.SpecialityViewItemActionDelegate
import mk.monthlytut.patient.views.viewholders.SpecialityViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.SpecialitiesVO

class SpecialityAdapter(private val mDelegate: SpecialityViewItemActionDelegate) :
        BaseRecyclerAdapter<SpecialityViewHolder, SpecialitiesVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {

                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_speciality, parent, false)
                return SpecialityViewHolder(view, mDelegate)

        }
    }

