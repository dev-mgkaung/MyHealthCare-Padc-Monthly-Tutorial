package mk.monthlytut.doctor.mvp.presenters


import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.doctor.mvp.views.ProfileView
import mk.padc.share.mvp.presenters.BasePresenter

interface ProfilePresenter : BasePresenter<ProfileView> {

    fun onUiReadyForProfile(context : Context, owner: LifecycleOwner)

    fun updateUserData(bitmap: Bitmap,
                       blood_type : String,dateofbirth : String, height : String, comment: String, phone: String)
}