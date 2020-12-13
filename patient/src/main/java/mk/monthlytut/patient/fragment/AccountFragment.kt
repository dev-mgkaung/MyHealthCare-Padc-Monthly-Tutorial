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
                startActivity(  activity?.applicationContext?.let{ ProfileActivity.newIntent(it)})
             dialog?.dismiss()
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

        etUserName.text = Editable.Factory.getInstance().newEditable( SessionManager.patient_name)
        etEmail.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_email)
        etphone.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_phone)
        et_dateofbirth.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_dateOfBirth)
        et_bloodtype.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_bloodType)
        et_height.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_height)
        et_comment.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_comment)

    }

    override fun hideProgressDialog() {}


}