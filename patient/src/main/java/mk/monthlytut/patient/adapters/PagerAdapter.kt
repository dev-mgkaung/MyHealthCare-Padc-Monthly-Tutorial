package mk.monthlytut.patient.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import mk.monthlytut.patient.fragment.GeneralQuestionFragment
import mk.monthlytut.patient.fragment.SpecialQuestionFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
      return   when(position)
        {
            0 ->  GeneralQuestionFragment()
            else -> SpecialQuestionFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}