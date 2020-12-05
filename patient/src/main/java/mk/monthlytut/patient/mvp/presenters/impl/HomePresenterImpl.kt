package mk.monthlytut.patient.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.patient.mvp.presenters.HomePresenter
import mk.monthlytut.patient.mvp.views.HomeView
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {

    private val patientModel : PatientModel = PatientModelImpl

    override fun onTapSpeciality(context: Context, specialitiesVO: SpecialitiesVO) {
        TODO("Not yet implemented")
    }

    override fun navigateToNextScreen() {
        TODO("Not yet implemented")
    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {

        patientModel.getSpecialities(onSuccess = {}, onError = {})

        patientModel.getSpecialitiesFromDB()
            .observe(owner, Observer {
                mView?.displaySpecialityList(it)
            })
    }

    override fun onTap() {
        TODO("Not yet implemented")
    }
}