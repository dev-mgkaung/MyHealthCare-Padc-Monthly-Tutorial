package mk.monthlytut.patient.mvp.views

import mk.padc.share.mvp.views.BaseView

interface ProfileView : BaseView {
    fun onTapSaveUserData()
    fun onTapCancelUserData()
    fun onTapEditProfileImage()
}