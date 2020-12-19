package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.PrescriptionInfoPresenter
import mk.monthlytut.doctor.mvp.presenters.PrescriptionPresenter
import mk.monthlytut.doctor.mvp.views.PrescriptionInfoView
import mk.monthlytut.doctor.mvp.views.PrescriptionView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class PrescriptionInfoPresenterImpl : PrescriptionInfoPresenter, AbstractBasePresenter<PrescriptionInfoView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl
    lateinit var mOwner: LifecycleOwner
    override fun onUiReadyForPrescription(consulation_chat_id: String) {

        doctorModel.getPrescription(consulation_chat_id, onSuccess = {}, onError = {})

        doctorModel.getPrescriptionFromDB(consulation_chat_id)
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