package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_case_summary.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.PagerAdapter
import mk.padc.share.activities.BaseActivity


class CaseSummaryActivity : BaseActivity()  {

    companion object {
        const val PARM_SPECIALITYID = "SPECIALITY ID"
        fun newIntent(
            context: Context,
            specialityID: String
        ) : Intent {
            val intent = Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(PARM_SPECIALITYID, specialityID)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_summary)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        pager?.adapter = PagerAdapter(supportFragmentManager)

        stepper_indicator.setViewPager(pager)


//
//        stepper_indicator.addOnStepClickListener {
//                step -> pager?.setCurrentItem(step, true) }

    }


}