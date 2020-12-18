package mk.monthlytut.patient.views.viewholders


import android.view.View
import kotlinx.android.synthetic.main.listitem_prescription_info.view.*
import mk.monthlytut.patient.delegates.PrescriptionInfoDelegate
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.views.viewholders.BaseViewHolder

class PrescriptionInfoViewHolder(itemView: View, private val mDelegate: PrescriptionInfoDelegate) :
        BaseViewHolder<PrescriptionVO>(itemView) {

    override fun bindData(data: PrescriptionVO) {

        data?.let {
            itemView.medicine_name.text = data.medicine
            itemView.txt_amount.text  = data.routineVO.amount +" Kyats"
            itemView.txt_quality.text = data.routineVO.quantity + " Tablet"
            itemView.txt_time.text = data.routineVO.days
            var times = data.routineVO.times.toString()
            itemView.txt_count.text = times
            itemView.txt_repeat.text  = data.routineVO.repeat
            itemView.txt_comment.text = data.routineVO.comment
        }

    }
}