package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.mvp.presenters.LoginPresenter
import mk.monthlytut.patient.mvp.presenters.impl.LoginPresenterImpl
import mk.monthlytut.patient.mvp.views.LoginView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.utils.setSafeOnClickListener

class LoginActivity : BaseActivity() , LoginView {

    private lateinit var mPresenter: LoginPresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {

        btnLogin.setSafeOnClickListener {
            mPresenter.onTapLogin(this,ed_email.text.toString(), ed_password.text.toString(),this)
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
        mPresenter.onUiReady(this,this)
    }

    override fun navigateToHomeScreen(patientVO: PatientVO) {

        SessionManager.login_status =true
        SessionManager.addPatientInfo(patientVO)
        startActivity(HomeActivity.newIntent(this))
        finish()
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }

}