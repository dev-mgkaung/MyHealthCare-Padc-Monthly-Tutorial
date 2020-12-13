package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.*
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.img_patient
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.txt_patient_dateofbirth
import kotlinx.android.synthetic.main.listitem_consultation_accept.view.txt_patient_name
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ConsultationDelegate
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder


class ConsultationViewHolder(itemView: View, private val mDelegate: ConsultationDelegate) :
        BaseViewHolder<ConsultationChatVO>(itemView) {

    override fun bindData(data: ConsultationChatVO) {

        data?.let {
            ImageUtils().showImage(itemView.img_patient,data.patient_info?.photo.toString(), R.drawable.user)
            itemView.txt_patient_name.text = data.patient_info?.name
            itemView.txt_patient_dateofbirth.text = data.start_consultation_date
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

        if(data.finish_consultation_status)
        {
            itemView.txt_send_message.visibility = View.GONE
        }else{
            itemView.txt_send_message.visibility = View.VISIBLE
        }

        itemView.txt_send_message.setOnClickListener {
            mDelegate.onTapSendMessage(data)
        }
    }
}