package mk.monthlytut.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_general_question.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.padc.share.activities.BaseFragment

class GeneralQuestionFragment : BaseFragment() {

   lateinit var listener : CaseSummaryCallBackListener

    companion object {

        @JvmStatic
        fun newInstance(listener : CaseSummaryCallBackListener) =
            GeneralQuestionFragment().apply {
               this.listener =listener
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_question, container, false)
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
    }
}