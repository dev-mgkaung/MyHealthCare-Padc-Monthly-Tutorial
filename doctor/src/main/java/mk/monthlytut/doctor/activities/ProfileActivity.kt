package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.acitvity_profile.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.mvp.presenters.ProfilePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.ProfilePresenterImpl
import mk.monthlytut.doctor.mvp.views.ProfileView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.utils.ImageUtils

class ProfileActivity : BaseActivity() , ProfileView {

    private lateinit var mPresenter: ProfilePresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_profile)
        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        imgedit.setOnClickListener {
            startActivity(this?.let { it1 -> EditProfileActivity.newIntent(it1) })
        }

        btnLogout.setOnClickListener {
            SessionManager.login_status=false
            startActivity(this?.let { it -> SplashActivity.newIntent(it) })
            this?.finish()
        }
    }
    private fun setUpPresenter() {
        mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
        mPresenter.onUiReady(this,this)
        mPresenter.onUiReadyForProfile(this,this)
    }

    override fun displayDocotrData(doctorVo: DoctorVO) {
        doctorVo?.let {
            SessionManager.addDoctorInfo(doctorVo)
        }

        ImageUtils().showImage(img_profile, doctorVo.photo.toString(),R.drawable.user)

        doctorname.text = Editable.Factory.getInstance().newEditable( SessionManager.doctor_name)
        doctorphone.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_phone)
        doctorspeciality.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_specialityname)
        doctor_dateofbirth.text =  " : " +Editable.Factory.getInstance().newEditable(SessionManager.doctor_dateofBirth)
        doctor_gender.text = " : " + Editable.Factory.getInstance().newEditable(SessionManager.doctor_gender)
        doctor_address.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_address)
        doctor_degree.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_degree)
        doctor_biography.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_bigraphy)
        doctor_experience.text = " : " + Editable.Factory.getInstance().newEditable(SessionManager.doctor_experience)
    }

    override fun hideProgressDialog() {}


}