package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.*
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.img_patient
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.txt_patient_dateofbirth
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.txt_patient_name
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ConsultationAcceptDelegate
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder


class ConsultationAcceptViewHolder(itemView: View, private val mDelegate: ConsultationAcceptDelegate) :
        BaseViewHolder<ConsultationRequestVO>(itemView) {

    override fun bindData(data: ConsultationRequestVO) {
        data?.let {
            ImageUtils().showImage(itemView.img_patient,data.patient_info?.photo.toString(), R.drawable.user)
            itemView.txt_patient_name.text = data.patient_info?.name
            itemView.txt_patient_dateofbirth.text = data.patient_info?.dateOfBirth
        }

        itemView.txt_consulated_history.setOnClickListener {
            mDelegate.onTapMedicalRecord(data)
        }

        itemView.txt_prescription.setOnClickListener {
            mDelegate.onTapPrescription(data)
        }

        itemView.txt_comment.setOnClickListener {
            mDelegate.onTapDoctorComment(data)
        }

        itemView.txt_send_message.setOnClickListener {
            mDelegate.onTapSendMessage(data)
        }
    }
}