package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import mk.monthlytut.patient.delegates.ConsultationAcceptDelegate
import mk.monthlytut.patient.delegates.RecentDoctorViewItemActionDelegate
import mk.monthlytut.patient.delegates.SpecialityViewItemActionDelegate
import mk.monthlytut.patient.mvp.views.HomeView
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.padc.share.mvp.presenters.BasePresenter


interface HomePresenter : BasePresenter<HomeView>
    , RecentDoctorViewItemActionDelegate , SpecialityViewItemActionDelegate , ConsultationAcceptDelegate{
    fun onTapSpeciality(context: Context, specialitiesVO: SpecialitiesVO)
    fun navigateToNextScreen()
    fun statusUpdateForCompleteType(context: Context, cosultation_chat_id : String, consultationRequestVO: ConsultationRequestVO)
}