package mk.monthlytut.patient.mvp.presenters


import android.graphics.Bitmap
import mk.monthlytut.patient.mvp.views.ProfileView
import mk.padc.share.mvp.presenters.BasePresenter

interface ProfilePresenter : BasePresenter<ProfileView> {
    fun updateUserProfile(bitmap: Bitmap)
    fun onTapCancelUserData()
    fun onTapEditProfileImage()
}