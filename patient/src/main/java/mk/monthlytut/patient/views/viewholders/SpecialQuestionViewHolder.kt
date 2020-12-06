package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_special_question.view.*
import mk.monthlytut.patient.delegates.SpecialQuestionDelegate
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.views.viewholders.BaseViewHolder

class SpecialQuestionViewHolder(itemView: View, private val mDelegate: SpecialQuestionDelegate) :
    BaseViewHolder<SpecialQuestionVO>(itemView) {


    override fun bindData(data: SpecialQuestionVO) {

        data?.let {
            itemView.txt_special_questions.text =data.sq_questions
        }

    }
}