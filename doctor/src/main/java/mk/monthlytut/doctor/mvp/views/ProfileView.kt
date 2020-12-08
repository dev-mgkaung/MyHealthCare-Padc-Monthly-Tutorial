
package mk.monthlytut.doctor.mvp.views
import mk.padc.share.mvp.views.BaseView

interface ProfileView : BaseView {
    fun onTapSaveUserData()
    fun onTapCancelUserData()
    fun onTapEditProfileImage()
}