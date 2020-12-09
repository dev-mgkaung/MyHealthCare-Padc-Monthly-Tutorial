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

            ImageUtils().showImage(itemView.img_patient,data.patient_info?.photo.toString(), R.drawable.user)
            itemView.txt_patient_name.text = data.patient_info?.name
            itemView.txt_patient_dateofbirth.text = data.patient_info?.dateOfBirth

            itemView.txt_patient_type.text =  itemView.resources.getString(R.string.new_patient)
            itemView.btnNext.visibility =View.GONE
            itemView.btnPostpone.visibility = View.GONE
            itemView.btnSkip.visibility = View.VISIBLE
//            if(data.patient_type_status.equals("new"))
//            {
//                itemView.txt_patient_type.text =  itemView.resources.getString(R.string.new_patient)
//                itemView.btnNext.visibility =View.GONE
//                itemView.btnPostpone.visibility = View.GONE
//                itemView.btnSkip.visibility = View.VISIBLE
//            }
//            else
//            {
//                itemView.txt_patient_type.text =  itemView.resources.getString(R.string.consulated_patient)
//                itemView.btnNext.visibility =View.VISIBLE
//                itemView.btnPostpone.visibility = View.VISIBLE
//                itemView.btnSkip.visibility = View.GONE
//            }
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