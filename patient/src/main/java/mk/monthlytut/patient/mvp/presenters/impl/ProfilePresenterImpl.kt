package mk.monthlytut.patient.mvp.presenters

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.mvp.views.ProfileView
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.PatientModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ProfilePresenterImpl : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val patientModel : PatientModel = PatientModelImpl

    override fun updateUserProfile(bitmap: Bitmap) {
        patientModel.uploadPhotoToFirebaseStorage(bitmap,
        onSuccess = {
            mView?.onTapSaveUserData()
            mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})
        },
        onFailure = {
            mView?.showError("Profile Update Failed")
        })

    }

    override fun onTapCancelUserData() {
        mView?.onTapCancelUserData()
    }

    override fun onTapEditProfileImage() {
        mView?.onTapEditProfileImage()
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}

