package mk.monthlytut.doctor.mvp.presenters

import mk.monthlytut.doctor.delegates.ConsultationDelegate
import mk.monthlytut.doctor.delegates.ConsultationRequestDelegate
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.padc.share.mvp.presenters.BasePresenter


interface HomePresenter : BasePresenter<HomeView> , ConsultationRequestDelegate, ConsultationDelegate
    {}