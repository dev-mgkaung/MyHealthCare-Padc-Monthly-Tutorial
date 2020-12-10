package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.PatientInfoPresenter
import mk.monthlytut.doctor.mvp.views.PatientInfoView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class PatientInfoPresenterImpl : PatientInfoPresenter, AbstractBasePresenter<PatientInfoView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl

    override fun onTapStartConsultation(consultation_chat_id: String) {

    }

    override fun onUiReadyConstulation(consulationRequestId: String , owner: LifecycleOwner) {
        doctorModel.getConsultationByConsulationRequestIdFromDB(consulationRequestId)
            .observe(owner, Observer {
                mView?.displayPatientInfo(it)
            })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}