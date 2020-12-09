package mk.monthlytut.doctor.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.mvp.presenters.LoginPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.LoginPresenterImpl
import mk.monthlytut.doctor.mvp.views.LoginView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.DoctorVO
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
        btnLogin.setOnClickListener {
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

    override fun navigateToMainScreen(doctorVO : DoctorVO) {

        SessionManager.login_status =true
        SessionManager.doctor_name = doctorVO.name
        SessionManager.doctor_id = doctorVO.id
        SessionManager.doctor_device_id = doctorVO.device_id
        SessionManager.doctor_email = doctorVO.email.toString()
        SessionManager.doctor_photo = doctorVO.photo.toString()
        SessionManager.doctor_speciality = doctorVO.speciality.toString()
        SessionManager.doctor_specialityname = doctorVO.specialityname.toString()
        SessionManager.doctor_phone = doctorVO.phone
        SessionManager.doctor_degree = doctorVO.degree
        SessionManager.doctor_bigraphy = doctorVO.biography
        startActivity(MainActivity.newIntent(this))
        this.finish()
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }

}