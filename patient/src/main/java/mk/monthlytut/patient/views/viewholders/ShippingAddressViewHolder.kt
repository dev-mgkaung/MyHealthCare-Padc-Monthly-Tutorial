package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_shipping_address.view.*
import mk.monthlytut.patient.delegates.ShippingAddressDelegate
import mk.padc.share.views.viewholders.BaseViewHolder

class ShippingAddressViewHolder(itemView: View, private val mDelegate: ShippingAddressDelegate) :
        BaseViewHolder<String>(itemView) {


    override fun bindData(data: String) {

        data?.let {
            itemView.listitem_address.text =data

        }

        itemView.listitem_address.setOnClickListener {
            mDelegate.onTapSelected(data)
        }

    }
}