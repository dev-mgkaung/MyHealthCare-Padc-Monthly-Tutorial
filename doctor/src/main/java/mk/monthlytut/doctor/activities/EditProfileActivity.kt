package mk.monthlytut.doctor.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.acitvity_profile.img_profile
import kotlinx.android.synthetic.main.edit_profile_activity.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.mvp.presenters.ProfilePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.ProfilePresenterImpl
import mk.monthlytut.doctor.mvp.views.ProfileView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.utils.ImageUtils
import java.io.IOException


class EditProfileActivity : BaseActivity()  , ProfileView {

    private lateinit var mPresenter: ProfilePresenter

    private  var bitmap : Bitmap? = null

    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var bloodType: String? = null
    private lateinit var  mProgreessDialog : ProgressDialog

    companion object {
        const val PICK_IMAGE_REQUEST = 1111
        fun newIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)

        setUpPresenter()
        setUpClickListener()
        setUpItemSelectedListener()
    }
    private fun setUpItemSelectedListener() {

    }
    private  fun setUpClickListener()
    {


        txt_upload.setOnClickListener {
            openGallery()
        }

        tex_back.setOnClickListener {
            onBackPressed()
        }

        mProgreessDialog = ProgressDialog(this)
        mProgreessDialog.setTitle("လုပ်ဆောင်နေပါသည်")
        mProgreessDialog.setMessage("ခဏစောင့်ဆိုင်းပေးပါ.....")

        btn_save.setOnClickListener {

            mProgreessDialog.show()
            var dateofbirth ="$day  $month $year"
            bitmap?.let { it1 -> mPresenter?.updateUserData(it1 ,
                    bloodType.toString()  ,dateofbirth,
                    pt_height.text.toString(),pt_comment.text.toString(),ptphone.text.toString()
            ) }

        }
    }

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun setUpPresenter() {
        this?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            this?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            val filePath = data.data
            try {
                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(this?.contentResolver!!, filePath)
                        bitmap = ImageDecoder.decodeBitmap(source)

                        ImageUtils().showImageProfile(img_profile.context,img_profile,null,filePath)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(this?.contentResolver, filePath)
                        ImageUtils().showImageProfile(img_profile.context,img_profile,null,filePath)

                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    override fun displayDocotrData(vo: DoctorVO) {
        TODO("Not yet implemented")
    }

    override fun hideProgressDialog() {
        mProgreessDialog.dismiss()
    }

}