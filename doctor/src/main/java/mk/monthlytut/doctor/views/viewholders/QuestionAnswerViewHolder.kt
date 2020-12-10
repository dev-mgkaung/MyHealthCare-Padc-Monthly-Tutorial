package mk.monthlytut.doctor.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_question_answer.view.*
import mk.monthlytut.doctor.delegates.QuestionAnswerDelegate
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.views.viewholders.BaseViewHolder

class QuestionAnswerViewHolder(itemView: View, private val mDelegate: QuestionAnswerDelegate) :
    BaseViewHolder<QuestionAnswerVO>(itemView) {

    override fun bindData(data: QuestionAnswerVO) {

        data?.let {
            itemView.txt_question.text = "(${adapterPosition}) "+ data.question
            itemView.txt_answer.text =data.answer
        }

    }
}