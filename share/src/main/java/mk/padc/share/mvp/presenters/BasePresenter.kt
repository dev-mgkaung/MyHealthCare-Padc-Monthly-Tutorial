package mk.padc.share.mvp.presenters
import androidx.lifecycle.LifecycleOwner
import mk.padc.share.mvp.views.BaseView
import android.content.Context

interface BasePresenter<T : BaseView> {
    fun initPresenter(view: T)
    fun onUiReady(context: Context,owner: LifecycleOwner)
}