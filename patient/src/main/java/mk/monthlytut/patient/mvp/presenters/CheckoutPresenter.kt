package mk.monthlytut.patient.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.delegates.CheckoutDelegate
import mk.monthlytut.patient.mvp.views.CheckOutView
import mk.padc.share.mvp.presenters.BasePresenter

interface CheckoutPresenter : BasePresenter<CheckOutView> , CheckoutDelegate {
    fun onUiReadyCheckout( consultationChatId : String ,  owner: LifecycleOwner)
    fun onTapCheckout()
}