package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_consultation_request.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ConsultationRequestDelegate
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder

class ConsultationRequestViewHolder(itemView: View, private val mDelegate: ConsultationRequestDelegate) :
        BaseViewHolder<ConsultationRequestVO>(itemView) {

    override fun bindData(data: ConsultationRequestVO) {

        data?.let {
          itemView.txt_patient_type.text = "New Patient"
            ImageUtils().showImage(itemView.img_patient,data.patient_info?.photo.toString(), R.drawable.user)
            itemView.txt_patient_name.text = data.patient_info?.name
            itemView.txt_patient_dateofbirth.text = data.patient_info?.dateOfBirth
        }

        itemView.btnAccept.setOnClickListener {
            mDelegate.onTapAccept(data)
        }

        itemView.btnNext.setOnClickListener {
            mDelegate.onTapNext(data)
        }

        itemView.btnPostpone.setOnClickListener {
            mDelegate.onTapPostpone(data)
        }

        itemView.btnSkip.setOnClickListener {
            mDelegate.onTapSkip(data)
        }
    }
}