package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.viewpod_consulation_request.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ConsultationAcceptDelegate
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder

class ConsultationAcceptViewHolder(itemView: View, private val mDelegate: ConsultationAcceptDelegate) :
        BaseViewHolder<ConsultationRequestVO>(itemView) {


    override fun bindData(data: ConsultationRequestVO) {

        data?.let {
//        itemView.txt_consulation.text = data.doctor_info?.specialityname + itemView.resources.getString(R.string.consultation_request_message)
//        ImageUtils().showImage(itemView.img_userprofile, data.doctor_info?.photo.toString(), R.drawable.doctor_thumbnail)
//        itemView.txt_doctorname.text = data.doctor_info?.name
//        itemView.txt_specialityname.text = data.doctor_info?.specialityname
//        itemView.txt_doctor_bigoraphy.text = data.doctor_info?.biography
        }

        itemView.btn_start.setOnClickListener {
            mDelegate.onTapStarted(consultationChatId = data.consultation_id.toString())
        }

    }
}