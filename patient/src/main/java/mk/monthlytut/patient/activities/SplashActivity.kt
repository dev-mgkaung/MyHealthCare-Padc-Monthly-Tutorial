package mk.monthlytut.patient.activities

import android.content.ClipData.newIntent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import mk.monthlytut.patient.R
import mk.padc.share.activities.BaseActivity


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        //3 seconds splash time
        Handler().postDelayed({
            //start main activity
            startActivity(HomeActivity.newIntent(this))
            //finish this activity
            finish()
        },2000)

    }
}