package mk.monthlytut.doctor.mvp.presenters.impl


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {

    private val doctorModel : DoctorModel = DoctorModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun onTapNext() {

    }

    override fun onTapSkip() {

    }

    override fun onTapPostpone() {

    }

    override fun onTapAccept() {

    }

    override fun onTapMedicalRecord() {

    }

    override fun onTapPrescription() {
    }

    override fun onTapSendMessage() {
    }

    override fun onTapDoctorComment() {
    }


}