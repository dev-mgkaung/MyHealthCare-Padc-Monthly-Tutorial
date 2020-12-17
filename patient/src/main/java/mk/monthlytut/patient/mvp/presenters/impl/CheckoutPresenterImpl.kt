package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.CheckoutPresenter
import mk.monthlytut.patient.mvp.views.CheckOutView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class CheckoutPresenterImpl : CheckoutPresenter, AbstractBasePresenter<CheckOutView>() {

    private val patientModel: PatientModel = PatientModelImpl
    private lateinit var mOwner : LifecycleOwner


    override fun onUiReadyCheckout(consultationChatId: String, owner: LifecycleOwner) {

        patientModel.getPrescription(consultationChatId, onSuccess = {}, onError = {})

        patientModel.getPrescriptionFromDB()
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayPrescription(it)
                    }
                })
    }

    override fun onTapCheckout() {

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner= owner
    }

}