package mk.padc.share.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import mk.padc.share.mvp.presenters.AbstractBasePresenter
import mk.padc.share.mvp.views.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDialog()
    }


    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }


    fun showDialog(){

    }

    inline fun <reified T : AbstractBasePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }

}