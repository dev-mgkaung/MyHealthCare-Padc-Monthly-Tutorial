package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.utils.ImageUtils

class MainActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drname.text= SessionManager.doctor_name
        ImageUtils().showImage(img_doctor,SessionManager.doctor_photo.toString(),R.drawable.doctor)

        setUpActionListeners()
    }

    private fun setUpActionListeners()
    {
        img_doctor.setOnClickListener {
            startActivity(ProfileActivity.newIntent(this))
        }
    }
}