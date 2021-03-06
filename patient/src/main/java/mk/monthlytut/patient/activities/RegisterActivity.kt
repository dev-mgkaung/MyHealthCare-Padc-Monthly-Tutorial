package mk.monthlytut.patient.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_register.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.mvp.presenters.RegisterPresenter
import mk.monthlytut.patient.mvp.presenters.impl.RegisterPresenterImpl
import mk.monthlytut.patient.mvp.views.RegisterView
import mk.padc.share.activities.BaseActivity

class RegisterActivity : BaseActivity() , RegisterView {

    private lateinit var mPresenter: RegisterPresenter
    private lateinit var token : String

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListeners()
    }


    private fun setUpActionListeners() {

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("fbToken", it.token)
            token =it.token
        }

        back.setOnClickListener{
            onBackPressed()
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(this,
                etUserName.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                token
            )
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
        mPresenter.onUiReady(this,this)
    }

    override fun navigateToToLoginScreen() {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
        startActivity(LoginActivity.newIntent(this))
    }
}