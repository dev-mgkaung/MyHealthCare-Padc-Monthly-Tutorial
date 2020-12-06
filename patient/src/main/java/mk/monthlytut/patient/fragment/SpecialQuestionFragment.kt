package mk.monthlytut.patient.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.padc.share.activities.BaseFragment

class SpecialQuestionFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_special_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpActionListener()
    }

    private fun setUpActionListener() {
    }

    private fun setUpPresenter() {
    }
}