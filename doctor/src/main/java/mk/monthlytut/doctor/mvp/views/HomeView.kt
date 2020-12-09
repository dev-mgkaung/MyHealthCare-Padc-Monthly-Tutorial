package mk.monthlytut.doctor.mvp.views

import mk.padc.share.mvp.views.BaseView

interface HomeView : BaseView {
  fun displayConsultationRequests()
  fun displayConsultationAcceptList()
}