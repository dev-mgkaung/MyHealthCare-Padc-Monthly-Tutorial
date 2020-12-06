package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.list_item_recent_doctor.view.txt_specialityname
import kotlinx.android.synthetic.main.list_item_speciality.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.SpecialityViewItemActionDelegate
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.views.viewholders.BaseViewHolder

class SpecialityViewHolder(itemView: View, private val mDelegate: SpecialityViewItemActionDelegate) :
    BaseViewHolder<SpecialitiesVO>(itemView) {


    override fun bindData(data: SpecialitiesVO) {

        data?.let {
            itemView.txt_specialityname.text =data.name
            data?.photo?.let{
                ImageUtils().showImage(itemView.img_speciality,it,  R.drawable.speciality_thumbnail)
            }
        }

        itemView.card_speciality.setOnClickListener{
            mDelegate.onTapSpeciality(data)
        }
    }
}