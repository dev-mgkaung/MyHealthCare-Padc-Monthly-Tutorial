package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_case_summary.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.PagerAdapter
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseActivity


class CaseSummaryActivity : BaseActivity() , CaseSummaryCallBackListener {


    companion object {
        const val PARM_SPECIALITYName= "SPECIALITY name"
        const val PARM_DOCTORVO = "PARM_DOCTORVO"
        fun newIntent(
            context: Context,
            specialityID: String,
            doctorVO : String
        ) : Intent {
            val intent = Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(PARM_SPECIALITYName, specialityID)
            intent.putExtra(PARM_DOCTORVO, doctorVO)
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

        val speciality = intent.getStringExtra(PARM_SPECIALITYName)
        val doctorVO = intent.getStringExtra(PARM_DOCTORVO)


        pager?.adapter =    PagerAdapter(supportFragmentManager, SessionManager.patient_email.toString(), speciality.toString(),doctorVO.toString(),this)
        stepper_indicator.setViewPager(pager)

//        stepper_indicator.addOnStepClickListener {
//                step -> pager?.setCurrentItem(step, true) }

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