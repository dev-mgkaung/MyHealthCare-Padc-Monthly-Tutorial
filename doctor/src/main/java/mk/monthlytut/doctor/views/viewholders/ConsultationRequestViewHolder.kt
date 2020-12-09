package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_consultation_request.view.*
import mk.monthlytut.doctor.delegates.ConsultationRequestDelegate
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.views.viewholders.BaseViewHolder

class ConsultationRequestViewHolder(itemView: View, private val mDelegate: ConsultationRequestDelegate) :
        BaseViewHolder<ConsultationRequestVO>(itemView) {

    override fun bindData(data: ConsultationRequestVO) {

        data?.let {
          itemView.txt_patient_type.text = "New Patient"
            // itemView.img_patient
            itemView.txt_patient_name.text = data.patient_info?.name
            itemView.txt_patient_dateofbirth.text = data.patient_info?.dateOfBirth
        }

        itemView.btnAccept.setOnClickListener {
            mDelegate.onTapAccept()
        }

        itemView.btnNext.setOnClickListener {
            mDelegate.onTapNext()
        }

        itemView.btnPostpone.setOnClickListener {
            mDelegate.onTapPostpone()
        }

        itemView.btnSkip.setOnClickListener {
            mDelegate.onTapSkip()
        }
    }
}