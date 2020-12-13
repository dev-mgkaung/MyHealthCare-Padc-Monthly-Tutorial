
package mk.monthlytut.doctor.mvp.views
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.mvp.views.BaseView

interface ProfileView : BaseView {
    fun displayDocotrData( vo : DoctorVO)
    fun hideProgressDialog()
}