package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_register.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.mvp.presenters.RegisterPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.RegisterPresenterImpl
import mk.monthlytut.doctor.mvp.views.RegisterView
import mk.padc.share.activities.BaseActivity

class RegisterActivity : BaseActivity() , RegisterView {

    private lateinit var mPresenter: RegisterPresenter
    private lateinit var token : String
    private var speciality_type: String? = null
    private var speciality_name: String? = null
    val specialityTypeList = mutableListOf(
        "cardiology",
        "dentist",
        "dermatology",
        "ent",
        "gastroenterology",
        "hepatology",
        "neurology",
        "og",
        "orthopedics",
        "pediartics",
        "radiology",
        "surgery"
    )
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpItemSelectedListeners()
        setUpActionListeners()
    }

    private fun setUpItemSelectedListeners(){
        specialname_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                speciality_name = parent.getItemAtPosition(position).toString()
                speciality_type = specialityTypeList[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setUpActionListeners() {

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("fbToken", it.token)
            token =it.token
        }


        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(this,
                etUserName.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                token,
                speciality_name.toString(),
                speciality_type.toString(),
                ed_phone.text.toString(),
                ed_degree.text.toString(),
                ed_biography.text.toString()
            )
        }

        back.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
        mPresenter.onUiReady(this,this)
    }

    override fun navigateToToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
        this.finish()
    }
}