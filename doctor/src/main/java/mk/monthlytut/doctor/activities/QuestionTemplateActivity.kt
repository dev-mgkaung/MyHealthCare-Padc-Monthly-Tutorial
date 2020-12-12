package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import mk.monthlytut.doctor.R
import mk.padc.share.activities.BaseActivity


class QuestionTemplateActivity : BaseActivity()
{
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, QuestionTemplateActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_template)
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