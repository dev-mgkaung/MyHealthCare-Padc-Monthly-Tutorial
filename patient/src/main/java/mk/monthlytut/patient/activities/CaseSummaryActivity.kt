package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_case_summary.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.PagerAdapter
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.padc.share.activities.BaseActivity
import mk.padc.share.utils.sharePreferencePatient
import mk.padc.share.utils.sharePreferencePatientEmail


class CaseSummaryActivity : BaseActivity() , CaseSummaryCallBackListener {



    companion object {
        const val PARM_SPECIALITYID = "SPECIALITY ID"
        const val PARM_EMAIL = "EMAIL ID"
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
            setHomeButtonEnabled(true)
        }

        tb_casesummary.setNavigationOnClickListener {
            onBackPressed()
        }

        val speciality = intent.getStringExtra(PARM_SPECIALITYID)

        val sharedPreferences = getSharedPreferences(sharePreferencePatient, Context.MODE_PRIVATE)
        val email = sharedPreferences.getString(sharePreferencePatientEmail, "")

        pager?.adapter = email?.let {
            PagerAdapter(supportFragmentManager,
                it.toString(), speciality.toString(),this)
        }

        stepper_indicator.setViewPager(pager)

        stepper_indicator.addOnStepClickListener {
                step -> pager?.setCurrentItem(step, true) }

    }

    @Override
    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onGeneralQuestionCallBack() {
        pager?.setCurrentItem(1, true)
    }

    override fun onSpecitalQuestionCallBack() {}
}