package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_question_template.view.*
import mk.monthlytut.doctor.delegates.MedicalDelegate
import mk.monthlytut.doctor.delegates.QuestionTemplateDelegate
import mk.padc.share.data.vos.GeneralQuestionTemplateVO
import mk.padc.share.data.vos.MedicineVO
import mk.padc.share.views.viewholders.BaseViewHolder

class MedicalViewHolder(itemView: View, private val mDelegate: MedicalDelegate) :
        BaseViewHolder<MedicineVO>(itemView) {

    override fun bindData(data: MedicineVO) {

        data?.let {
          //  itemView.question_template.text = data.question
        }

        itemView.question_template.setOnClickListener {
         //   mDelegate.onTapOneQuestion(data)
        }
    }
}