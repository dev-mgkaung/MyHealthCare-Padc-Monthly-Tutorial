package mk.monthlytut.patient.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.profile_data_dialog.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.activities.ProfileActivity
import mk.monthlytut.patient.activities.SplashActivity
import mk.monthlytut.patient.mvp.presenters.ProfilePresenter
import mk.monthlytut.patient.mvp.presenters.ProfilePresenterImpl
import mk.monthlytut.patient.mvp.views.ProfileView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.utils.ImageUtils

class AccountFragment : BaseFragment() ,ProfileView{

    private lateinit var mPresenter: ProfilePresenter

    companion object {
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

        setUpActionListener()
        setUpPresenter()

        imgedit.setOnClickListener {
            startActivity(activity?.let { it1 -> ProfileActivity.newIntent(it1) })
        }

    }

    private fun setUpPresenter() {
        this?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            activity?.let { it1 -> mPresenter.onUiReadyForAccountFragment(it1,this) }
        }
    }

 private fun checkPatientInfoDialog()
 {

     if(SessionManager.patient_bloodType.toString().isEmpty())
     {
         val view = layoutInflater.inflate(R.layout.profile_data_dialog, null)
         val dialog = context?.let { Dialog(it) }
         dialog?.window?.setLayout(
             WindowManager.LayoutParams.MATCH_PARENT,
             WindowManager.LayoutParams.MATCH_PARENT
         )
         dialog?.apply {
             setCancelable(false)
             setContentView(view)
             window?.setBackgroundDrawableResource(android.R.color.transparent)
         }

         view.android_cancel.setOnClickListener {
             dialog?.dismiss()
         }

         view.btn_fill_patient.setOnClickListener {
                dialog?.dismiss()
                startActivity(  activity?.applicationContext?.let{ ProfileActivity.newIntent(it)})
         }
         dialog?.show()
     }
 }


    private fun setUpActionListener()
    {
        btnLogout.setOnClickListener {
            SessionManager.login_status=false
            startActivity(activity?.let { it -> SplashActivity.newIntent(it) })
            activity?.finish()
        }
    }

    override fun displayPatientData(patientVO: PatientVO) {

        patientVO?.let {
            SessionManager.addPatientInfo(patientVO)
            checkPatientInfoDialog()
        }

        ImageUtils().showImage(img_profile, patientVO.photo.toString(),R.drawable.user)

        etUserName.text = SessionManager.patient_name
        etEmail.text = SessionManager.patient_email

        if(SessionManager.patient_phone.toString().isNotEmpty()) {
            etphone.text = SessionManager.patient_phone
        }else
        {
            etphone.text = resources.getString(R.string.profile_data)
        }


        if(SessionManager.patient_dateOfBirth.toString().isNotEmpty()) {
            et_dateofbirth.text = SessionManager.patient_dateOfBirth
        }else
        {
            et_dateofbirth.text = resources.getString(R.string.profile_data)
        }

        if(SessionManager.patient_bloodType.toString().isNotEmpty()) {
            et_bloodtype.text = SessionManager.patient_bloodType
        }else
        {
            et_bloodtype.text = resources.getString(R.string.profile_data)
        }

        if(SessionManager.patient_height.toString().isNotEmpty()) {
            et_height.text = SessionManager.patient_height
        }else
        {
            et_height.text = resources.getString(R.string.profile_data)
        }

        if(SessionManager.patient_comment.toString().isNotEmpty()) {
            et_comment.text = SessionManager.patient_comment
        }else
        {
            et_comment.text = resources.getString(R.string.profile_data)
        }



    }

    override fun hideProgressDialog() {}


}