package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.list_item_recent_doctor.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.RecentDoctorViewItemActionDelegate
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.RecentDoctorVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder

class RecentDoctorViewHolder(itemView: View, private val mDelegate: RecentDoctorViewItemActionDelegate) :
        BaseViewHolder<RecentDoctorVO>(itemView) {

    override fun bindData(data: RecentDoctorVO) {
       data?.let {
           itemView.txt_doctorname.text =data?.name
           itemView.txt_specialityname.text =data?.specialityname
           data?.photo?.let{
               ImageUtils().showImage(itemView.img_doctor_profile,it, R.drawable.doctor_thumbnail)
           }

       }
    }
}