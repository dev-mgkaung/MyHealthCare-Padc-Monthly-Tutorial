package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.MedicalDelegate
import mk.monthlytut.doctor.views.viewholders.MedicalViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.MedicineVO

class MedicalAdapter(private val mDelegate: MedicalDelegate) :
        BaseRecyclerAdapter<MedicalViewHolder, MedicineVO>() {

    fun setMedicineList(list : ArrayList<MedicineVO>)
    {
        mData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_medicine, parent, false)
        return MedicalViewHolder(view, mDelegate)

    }
}
