package mk.monthlytut.patient.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_special_question.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.SpecialQuestionAdapter
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.monthlytut.patient.mvp.presenters.CaseSummaryPresenter
import mk.monthlytut.patient.mvp.presenters.impl.CaseSummaryPresenterImpl
import mk.monthlytut.patient.mvp.views.CaseSummaryView
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO

private const val ARG_PARAM = "speciality"
class SpecialQuestionFragment : BaseFragment() ,CaseSummaryView{

    lateinit var listener : CaseSummaryCallBackListener

    private lateinit var mPresenter: CaseSummaryPresenter

    private lateinit var adapter: SpecialQuestionAdapter

    private  var questionAnswerList : ArrayList<QuestionAnswerVO> = arrayListOf()

    private var speciality: String? = null

    companion object {

        @JvmStatic
        fun newInstance(mSpeciality : String, listener : CaseSummaryCallBackListener) =
            SpecialQuestionFragment().apply {
                this.listener =listener
                this.speciality=mSpeciality
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_special_question, container, false)
        arguments?.let {
            speciality = it.getString(ARG_PARAM)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListener()
    }

    private fun setUpRecyclerView() {

        rc_specialquestion.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = SpecialQuestionAdapter(mPresenter)
        rc_specialquestion.adapter = adapter
    }

    private fun setUpActionListener() {
        btn_confirm.setOnClickListener {
            for(item in questionAnswerList)
            {
                Log.d("data=",item.answer.toString())
            }
            listener.onSpecitalQuestionCallBack()
        }
    }

    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
            mPresenter.onUiReadyforSpecialQuestion(it, speciality.toString(),this)
        }
    }

    override fun displaySpecialQuestions(list: List<SpecialQuestionVO>) {
            adapter.setNewData(list.toMutableList())
            questionAnswerList.clear()
            for(item in list)
            {
            questionAnswerList.add(QuestionAnswerVO(item.id,item.sq_questions,""))
            }
            adapter.setQuestionAnswerList(questionAnswerList)
    }

    override fun displayOnceGeneralQuestion() {}

    override fun displayAlwaysGeneralQuestion() {}

    override fun replaceQuestionAnswerList(position: Int, questionanswervo : QuestionAnswerVO) {
        questionAnswerList.set(position,questionanswervo)
    }
}