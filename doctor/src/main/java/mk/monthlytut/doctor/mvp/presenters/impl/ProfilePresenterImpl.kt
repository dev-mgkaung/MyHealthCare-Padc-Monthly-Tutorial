package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.mvp.presenters.ProfilePresenter
import mk.monthlytut.doctor.mvp.views.ProfileView
import mk.padc.share.data.models.AuthenticationModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.AuthenticationModelImpl
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.mvp.presenters.AbstractBasePresenter

class ProfilePresenterImpl : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mModel : DoctorModel = DoctorModelImpl

    override fun updateUserProfile(bitmap: Bitmap) {
        mModel.uploadPhotoToFirebaseStorage(bitmap,
        onSuccess = {

            mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})
        },
        onFailure = {
            mView?.showError("Profile Update Failed")
        })

    }

    override fun onTapCancelUserData() {

    }

    override fun onTapEditProfileImage() {

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}

