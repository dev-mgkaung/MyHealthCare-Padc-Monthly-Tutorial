package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import mk.monthlytut.doctor.R
import mk.padc.share.activities.BaseActivity

class MedicalCommentAcitivity : BaseActivity()
{
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, MedicalCommentAcitivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record)
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