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

    }

    override fun onTapSpeciality(specialitiesVO: SpecialitiesVO) {
        mView?.nextPageToCaseSummary(specialitiesVO)
    }

    override fun navigateToNextScreen() {

    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {

        patientModel.getSpecialities(onSuccess = {}, onError = {})

        patientModel.getSpecialitiesFromDB()
            .observe(owner, Observer {
                mView?.displaySpecialityList(it)
            })

        patientModel.getRecentDoctors("a1f93420-3007-11eb-ae84-dda1e0ee4767" , onSuccess = {}, onError = {})

        patientModel.getRecentDoctorsFromDB()
            .observe(owner, Observer {
                mView?.displayRecentDoctorList(it)
            })
    }

    override fun onTap() {

    }


}