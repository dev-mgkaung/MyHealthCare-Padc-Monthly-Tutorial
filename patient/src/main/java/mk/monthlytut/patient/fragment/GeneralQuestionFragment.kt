package mk.monthlytut.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_general_question.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.monthlytut.patient.mvp.presenters.CaseSummaryPresenter
import mk.monthlytut.patient.mvp.presenters.impl.CaseSummaryPresenterImpl
import mk.monthlytut.patient.mvp.views.CaseSummaryView
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.SpecialQuestionVO

private const val ARG_PARAM = "email"
class GeneralQuestionFragment : BaseFragment(), CaseSummaryView {

    lateinit var listener : CaseSummaryCallBackListener

    private lateinit var mPresenter: CaseSummaryPresenter

    private var email: String? = null

    companion object {

        @JvmStatic
        fun newInstance(email: String , listener : CaseSummaryCallBackListener) =
            GeneralQuestionFragment().apply {
               this.listener =listener
                this.email=email
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_general_question, container, false)
        arguments?.let {
            email = it.getString(ARG_PARAM)
        }
        return view
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpActionListener()
    }

    private fun setUpActionListener() {
        btn_next.setOnClickListener {
            listener.onGeneralQuestionCallBack()
        }
    }

    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
            mPresenter.onUiReadyforGeneralQuestion(it, email.toString(),this)
        }
    }

    override fun displaySpecialQuestions(list: List<SpecialQuestionVO>) {}

    override fun displayOnceGeneralQuestion() {
        card_userinfo.visibility = View.GONE
        ly_onetime_fil.visibility= View.VISIBLE
    }

    override fun displayAlwaysGeneralQuestion() {
        card_userinfo.visibility = View.VISIBLE
        ly_onetime_fil.visibility =View.GONE
    }
}