package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ShippingAddressDelegate
import mk.monthlytut.patient.views.viewholders.ShippingAddressViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter

class ShippingAddressAdapter( private val mDelegate: ShippingAddressDelegate, var mpreviousPosition: Int) :
        BaseRecyclerAdapter<ShippingAddressViewHolder, String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingAddressViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_shipping_address, parent, false)
        return ShippingAddressViewHolder(view,  mpreviousPosition, mDelegate)

    }
}
