package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.mvp.presenters.ProfilePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.ProfilePresenterImpl
import mk.monthlytut.doctor.mvp.views.ProfileView
import mk.padc.share.activities.BaseActivity

class ProfileActivity : BaseActivity() , ProfileView {

    private lateinit var mPresenter: ProfilePresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_profile)
        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {

    }
    private fun setUpPresenter() {
        mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
        mPresenter.onUiReady(this,this)
    }

    override fun onTapSaveUserData() {

    }

    override fun onTapCancelUserData() {
    }

    override fun onTapEditProfileImage() {
    }
}