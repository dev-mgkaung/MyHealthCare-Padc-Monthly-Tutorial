package mk.monthlytut.patient.mvp.presenters.impl


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.PrescriptionInfoPresenter
import mk.monthlytut.patient.mvp.views.PrescriptionInfoView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class PrescriptionInfoPresenterImpl : PrescriptionInfoPresenter, AbstractBasePresenter<PrescriptionInfoView>() {

    private val mModel: PatientModel = PatientModelImpl
    lateinit var mOwner: LifecycleOwner

    override fun onUiReadyForPrescription(consulation_chat_id: String) {

        mModel.getPrescription(consulation_chat_id, onSuccess = {}, onError = {})

        mModel.getPrescriptionFromDB(consulation_chat_id)
                .observe(mOwner, Observer {
                    it?.let{
                        mView?.displayPrescriptionList(it)
                    }
                })

    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner = owner
    }

}