package mk.monthlytut.patient.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_account.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.activities.SplashActivity
import mk.monthlytut.patient.mvp.presenters.ProfilePresenter
import mk.monthlytut.patient.mvp.presenters.ProfilePresenterImpl
import mk.monthlytut.patient.mvp.views.ProfileView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseFragment
import mk.padc.share.utils.*
import java.io.IOException

class AccountFragment : BaseFragment() ,ProfileView {

    private lateinit var mPresenter: ProfilePresenter

    private  var bitmap : Bitmap? = null

    companion object {
        const val PICK_IMAGE_REQUEST = 1111
        @JvmStatic
        fun newInstance() = AccountFragment().apply {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpActionListener()

        etUserName.text = Editable.Factory.getInstance().newEditable( SessionManager.patient_name)
        etEmail.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_email)

    }



    private fun setUpActionListener()
    {
        img_edit.setOnClickListener{
            mPresenter?.onTapEditProfileImage()
        }
        tv_save.setOnClickListener{
            bitmap?.let { }
        }
        tv_cancel.setOnClickListener{
            bitmap?.let {
                mPresenter.onTapCancelUserData()
            }
        }
        btnLogout.setOnClickListener {
            SessionManager.login_status=false
            startActivity(activity?.let { it -> SplashActivity.newIntent(it) })
            activity?.finish()
        }
    }
    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }
    }


    override fun onTapSaveUserData() {
        account_btngroup.visibility = View.GONE
      //  context?.let { it1 -> mPresenter.updatePatent(it1,this) }
    }

    override fun onTapCancelUserData() {
        account_btngroup.visibility = View.GONE
    }

    override fun onTapEditProfileImage() {
        openGallery()
    }

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), AccountFragment.PICK_IMAGE_REQUEST)
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AccountFragment.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            val filePath = data.data
            try {
                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, filePath)
                        bitmap = ImageDecoder.decodeBitmap(source)
                        account_btngroup.visibility = View.VISIBLE
                        ImageUtils().showImageProfile(img_profile.context,img_profile,null,filePath)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, filePath)
                        ImageUtils().showImageProfile(img_profile.context,img_profile,null,filePath)
                        account_btngroup.visibility = View.GONE
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}