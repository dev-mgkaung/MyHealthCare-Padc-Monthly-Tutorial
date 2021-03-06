package mk.padc.share.mvp.presenters
import androidx.lifecycle.LifecycleOwner
import mk.padc.share.mvp.views.BaseView
import android.content.Context
import mk.padc.share.data.vos.PatientVO

interface BasePresenter<T : BaseView> {
    fun initPresenter(view: T)
    fun onUiReady(context: Context,owner: LifecycleOwner)

}