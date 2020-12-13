package mk.monthlytut.doctor.views.viewholders

import android.text.Editable
import android.view.View
import kotlinx.android.synthetic.main.listitem_question_template.view.*
import mk.monthlytut.doctor.delegates.QuestionTemplateDelegate
import mk.padc.share.data.vos.GeneralQuestionTemplateVO
import mk.padc.share.views.viewholders.BaseViewHolder

class QuestionTemplateViewHolder(itemView: View, private val mDelegate: QuestionTemplateDelegate) :
        BaseViewHolder<GeneralQuestionTemplateVO>(itemView) {

    override fun bindData(data: GeneralQuestionTemplateVO) {

        data?.let {
            itemView.question_template.text = data.question
        }

        itemView.question_template.setOnClickListener {
            mDelegate.onTapOneQuestion(data)
        }
    }
}