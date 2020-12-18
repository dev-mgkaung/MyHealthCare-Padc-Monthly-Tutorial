package mk.monthlytut.patient.mvp.presenters


import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import mk.monthlytut.patient.mvp.views.ProfileView
import mk.padc.share.mvp.presenters.BasePresenter

interface ProfilePresenter : BasePresenter<ProfileView> {

    fun onUiReadyForAccountFragment(context : Context, owner: LifecycleOwner)

    fun updateUserData(bitmap: Bitmap,
    blood_type : String,dateofbirth : String, height : String, comment: String, phone: String,address : String)
}