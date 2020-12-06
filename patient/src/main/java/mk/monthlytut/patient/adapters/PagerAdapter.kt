package mk.monthlytut.patient.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.monthlytut.patient.fragment.GeneralQuestionFragment
import mk.monthlytut.patient.fragment.SpecialQuestionFragment

class PagerAdapter(fragmentManager: FragmentManager, var email : String,var speciality: String ,var listener : CaseSummaryCallBackListener) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
      return   when(position)
        {
            0 ->  GeneralQuestionFragment.newInstance(email ,listener)
            else -> SpecialQuestionFragment.newInstance(speciality,listener)
        }
    }

    override fun getCount(): Int {
        return 2
    }
}