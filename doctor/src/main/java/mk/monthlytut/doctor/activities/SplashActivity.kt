package mk.monthlytut.doctor.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity

class SplashActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SplashActivity::class.java).
                apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(!SessionManager.login_status) {
                startActivity(LoginActivity.newIntent(this))
            }else{
                startActivity(MainActivity.newIntent(this))
            }
            finish()
        },1800)

    }
}