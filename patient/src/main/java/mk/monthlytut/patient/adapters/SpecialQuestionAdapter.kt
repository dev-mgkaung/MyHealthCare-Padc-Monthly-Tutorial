package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.SpecialQuestionDelegate
import mk.monthlytut.patient.views.viewholders.SpecialQuestionViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO

class SpecialQuestionAdapter(private val mDelegate: SpecialQuestionDelegate
) : BaseRecyclerAdapter<SpecialQuestionViewHolder, SpecialQuestionVO>() {


    var mQuestionAnswerList: List<QuestionAnswerVO> = arrayListOf()

     fun setQuestionAnswerList( questionAnswerList: List<QuestionAnswerVO>)
    {
        mQuestionAnswerList =questionAnswerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialQuestionViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_special_question, parent, false)
        return SpecialQuestionViewHolder(view,mQuestionAnswerList, mDelegate)
    }

    override fun onBindViewHolder(holder: SpecialQuestionViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

}

