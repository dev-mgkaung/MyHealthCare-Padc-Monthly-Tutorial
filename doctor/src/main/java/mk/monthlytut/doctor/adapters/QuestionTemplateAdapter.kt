package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.QuestionTemplateDelegate
import mk.monthlytut.doctor.views.viewholders.QuestionTemplateViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.GeneralQuestionTemplateVO

class QuestionTemplateAdapter(private val mDelegate: QuestionTemplateDelegate) :
        BaseRecyclerAdapter<QuestionTemplateViewHolder, GeneralQuestionTemplateVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionTemplateViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_question_template, parent, false)
        return QuestionTemplateViewHolder(view, mDelegate)

    }
}
