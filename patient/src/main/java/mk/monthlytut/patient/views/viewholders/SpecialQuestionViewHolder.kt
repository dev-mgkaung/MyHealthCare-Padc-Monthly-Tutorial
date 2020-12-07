package mk.monthlytut.patient.views.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.listitem_special_question.view.*
import mk.monthlytut.patient.delegates.SpecialQuestionDelegate
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.padc.share.views.viewholders.BaseViewHolder


class SpecialQuestionViewHolder(itemView: View, var mQuestionAnswerList: List<QuestionAnswerVO>, private val mDelegate: SpecialQuestionDelegate
) : BaseViewHolder<SpecialQuestionVO>(itemView) {

    override fun bindData(data: SpecialQuestionVO) {

            data?.let {
            itemView.txt_special_questions.text = "(${adapterPosition+1}) ${data.sq_questions}"
             }

            mQuestionAnswerList?.let {
               itemView.ed_answer.text = Editable.Factory.getInstance().newEditable(mQuestionAnswerList[adapterPosition].answer)
            }

            itemView.ed_answer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                var questionAnswerVO= QuestionAnswerVO(data.id,data.sq_questions,itemView.ed_answer.text.toString())
                mDelegate.onAnswerChange(adapterPosition,questionAnswerVO)
            }

            override fun afterTextChanged(editable: Editable) {}
        })

    }
}