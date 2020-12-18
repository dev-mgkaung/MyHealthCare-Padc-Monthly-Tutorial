package mk.monthlytut.patient.views.viewholders

import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.listitem_shipping_address.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ShippingAddressDelegate
import mk.padc.share.views.viewholders.BaseViewHolder

class ShippingAddressViewHolder(itemView: View, var previousPosition : Int,private val mDelegate: ShippingAddressDelegate) :
        BaseViewHolder<String>(itemView) {

    override fun bindData(data: String) {
        itemView.listitem_address.setBackgroundResource(R.drawable.bg_rounded_border_grey)
        Log.d("previous Postion ${previousPosition}","currentPosition ${adapterPosition}")
        if(adapterPosition == previousPosition)
        {
            itemView.listitem_address.setBackgroundResource(R.drawable.bg_rounded_corner_blue)
        }
        data?.let {
            itemView.listitem_address.text =data
        }

        itemView.listitem_address.setOnClickListener {
            mDelegate.onTapSelected(data,adapterPosition)
        }

    }
}