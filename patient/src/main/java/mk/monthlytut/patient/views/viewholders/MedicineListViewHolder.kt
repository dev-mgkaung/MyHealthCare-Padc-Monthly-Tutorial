package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.medicine_listitem.view.*
import mk.monthlytut.patient.delegates.CheckoutDelegate
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.views.viewholders.BaseViewHolder


class MedicineListViewHolder(itemView: View, private val mDelegate: CheckoutDelegate) :
        BaseViewHolder<PrescriptionVO>(itemView) {

    override fun bindData(data: PrescriptionVO) {

        data?.let {
            itemView.txt_price.text = data.price +" ကျပ်"
            itemView.txt_tablet.text = data.count +" ကတ်"
            itemView.txt_medicinename.text = data.medicine
        }

    }
}