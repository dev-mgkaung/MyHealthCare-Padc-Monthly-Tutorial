package mk.monthlytut.patient.views.viewholders

import android.view.View
import kotlinx.android.synthetic.main.listitem_special_question.view.*
import mk.monthlytut.patient.delegates.SpecialQuestionDelegate
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.views.viewholders.BaseViewHolder


class SpecialQuestionViewHolder(itemView: View, private val mDelegate: SpecialQuestionDelegate
) : BaseViewHolder<SpecialQuestionVO>(itemView) {

    override fun bindData(data: SpecialQuestionVO) {
      //  questionAnswerList[adapterPosition].question = data.sq_questions

            data?.let {
            itemView.txt_special_questions.text = "(${adapterPosition+1}) ${data.sq_questions}"
             }

//        itemView.ed_answer.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
//                questionAnswerList[adapterPosition].answer = itemView.ed_answer.text.toString()
//            }
//
//            override fun afterTextChanged(editable: Editable) {}
//        })

    }
}