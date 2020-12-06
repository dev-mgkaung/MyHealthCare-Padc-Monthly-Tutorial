package mk.monthlytut.patient.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.monthlytut.patient.fragment.GeneralQuestionFragment
import mk.monthlytut.patient.fragment.SpecialQuestionFragment

class PagerAdapter(fragmentManager: FragmentManager, var listener : CaseSummaryCallBackListener) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
      return   when(position)
        {
            0 ->  GeneralQuestionFragment.newInstance(listener)
            else -> SpecialQuestionFragment.newInstance(listener)
        }
    }

    override fun getCount(): Int {
        return 2
    }
}