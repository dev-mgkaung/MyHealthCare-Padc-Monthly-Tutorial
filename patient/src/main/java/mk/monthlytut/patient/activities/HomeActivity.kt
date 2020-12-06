package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import mk.monthlytut.patient.R
import mk.padc.share.activities.BaseActivity

class HomeActivity : BaseActivity() {

    companion object {
        //MainActivity call from other screen
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }
}