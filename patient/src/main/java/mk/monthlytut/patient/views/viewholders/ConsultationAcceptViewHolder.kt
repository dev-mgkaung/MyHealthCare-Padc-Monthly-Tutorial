package mk.monthlytut.patient.views.viewholders

import android.view.View
import mk.monthlytut.patient.delegates.ConsultationAcceptDelegate
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.views.viewholders.BaseViewHolder

class ConsultationAcceptViewHolder(itemView: View, private val mDelegate: ConsultationAcceptDelegate) :
        BaseViewHolder<ConsultationRequestVO>(itemView) {


    override fun bindData(data: ConsultationRequestVO) {

        data?.let {

        }

    }
}