package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.QuestionAnswerDelegate
import mk.monthlytut.doctor.views.viewholders.QuestionAnswerViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.QuestionAnswerVO

class QuestionAnswerAdapter(private val mDelegate: QuestionAnswerDelegate) :
    BaseRecyclerAdapter<QuestionAnswerViewHolder, QuestionAnswerVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAnswerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_question_answer, parent, false)
        return QuestionAnswerViewHolder(view, mDelegate)

    }
}
