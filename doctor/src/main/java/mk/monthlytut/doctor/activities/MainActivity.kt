package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity

class MainActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drname.text= SessionManager.doctor_name
    }
}