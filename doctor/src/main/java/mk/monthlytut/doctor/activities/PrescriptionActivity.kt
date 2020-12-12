package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import mk.monthlytut.doctor.R
import mk.padc.share.activities.BaseActivity

class PrescriptionActivity : BaseActivity()
{
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, PrescriptionActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)
        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {

    }
    private fun setUpPresenter() {
//        mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
//        mPresenter.onUiReady(this,this)
    }

}