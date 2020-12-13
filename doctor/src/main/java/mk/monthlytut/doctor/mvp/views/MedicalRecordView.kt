package mk.monthlytut.doctor.mvp.views

import mk.padc.share.mvp.views.BaseView

interface MedicalRecordView : BaseView {
    fun showSnackBar(message : String)
}