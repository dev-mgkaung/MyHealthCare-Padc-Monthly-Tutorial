package mk.monthlytut.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.medical_record_dialog.view.*
import kotlinx.android.synthetic.main.postpone_dialog.view.confirm
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.ConsultationAdapter
import mk.monthlytut.doctor.adapters.ConsultationRequestAdapter
import mk.monthlytut.doctor.dialogs.PatientInfoDialog
import mk.monthlytut.doctor.dialogs.PrescriptionDialog
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.HomePresenterImpl
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.ConsultedPatientVO
import mk.padc.share.utils.ImageUtils

class MainActivity : BaseActivity() ,HomeView {

    private lateinit var mPresenter: HomePresenter

    private lateinit var consultationRequestAdapter:  ConsultationRequestAdapter
    private lateinit var consultationAcceptAdapter:  ConsultationAdapter
    private  var requestDummyList : List<ConsultationRequestVO> = arrayListOf()
    private  var acceptDummyList : List<ConsultationChatVO> = arrayListOf()

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("fbToken", it.token)
        }
        drname.text= SessionManager.doctor_name
        ImageUtils().showImage(img_doctor,SessionManager.doctor_photo.toString(),R.drawable.doctor_thumbnail)

        setUpPresenter()
        setUpActionListeners()
        setUpRecyclerView()

        Firebase.messaging.subscribeToTopic(SessionManager.doctor_speciality.toString())
                .addOnCompleteListener { task ->
                    var msg = "Subscribed"
                    if (!task.isSuccessful) {
                        msg = "Failed"
                    }
                }
    }

    private fun setUpPresenter()
    {
            mPresenter = getPresenter<HomePresenterImpl, HomeView>()
            mPresenter.onUiReady(this,this)
    }

    private fun setUpActionListeners()
    {
        img_doctor.setOnClickListener {
            this.finish()
            startActivity(ProfileActivity.newIntent(this))
        }
    }
    private fun setUpRecyclerView()
    {
        rc_consulation_request.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        consultationRequestAdapter = ConsultationRequestAdapter (mPresenter)
        rc_consulation_request.adapter = consultationRequestAdapter

        rc_consulation_accept.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        consultationAcceptAdapter = ConsultationAdapter (mPresenter)
        rc_consulation_accept.adapter = consultationAcceptAdapter
    }

    override fun displayConsultationRequests(list: List<ConsultationRequestVO>) {
        requestDummyList= list.toMutableList()
        consultationRequestAdapter.setNewData(list.toMutableList())
        showHideEmptyView()
    }

    fun showHideEmptyView()
    {
        if(acceptDummyList?.size == 0 && requestDummyList?.size== 0)
        {
            empty_view.visibility =View.VISIBLE
        }else{
            empty_view.visibility =View.GONE
        }
    }
    override fun displayConsultationList(list: List<ConsultationChatVO>) {
        acceptDummyList= list.toMutableList()
        consultationAcceptAdapter.setNewData(list.toMutableList())
        if(list?.size == 0) {
            consultationlabel.visibility = View.GONE
        }else
        {
            consultationlabel.visibility = View.VISIBLE
        }
        showHideEmptyView()
    }

    override fun displayConsultedPatient(list: List<ConsultedPatientVO>) {
        consultationRequestAdapter.setConsultedPatientList(list.toMutableList())

    }

    override fun nextPageChatRoom(consultation_id : String) {
        startActivity(consultation_id?.let { ChatRoomActvity.newIntent(this, it) })
        this.finish()
    }

    override fun nextPagePatientInfo(consultation_request_id: String) {
       startActivity(consultation_request_id?.let { PatientInfoActivity.newIntent(this, it) })
    }

    override fun displayPostPoneChooserDialog(consultationRequestVO: ConsultationRequestVO) {
        val view = layoutInflater.inflate(R.layout.postpone_dialog, null)
        val dialog = this?.let { Dialog(it) }
        val timePicker = view?.findViewById<TimePicker>(R.id.timePicker)
        var msg : String =""
        timePicker?.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }

                val h = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                 msg = " $h : $min $am_pm"

        }
        dialog?.apply {
            setCancelable(true)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.confirm.setOnClickListener {
            msg?.let{mPresenter.onTapPostponeTime(it,consultationRequestVO)}
            dialog?.dismiss()
        }
        dialog?.show()
    }

    override fun displayPatientInfoDialog(consultationChatVO: ConsultationChatVO)
    {
        var data=  Gson().toJson(consultationChatVO)
        consultationChatVO?.let {
            val dialog: PatientInfoDialog = PatientInfoDialog.newInstance(data)
            dialog.show(supportFragmentManager, "")
        }
    }

    override fun displayPrescriptionDialog( consultation_id: String ,patient_name: String, start_conservation_date: String) {

            val dialog: PrescriptionDialog = PrescriptionDialog.newInstance(consultation_id,patient_name,start_conservation_date)
            dialog.show(supportFragmentManager, "")
    }

    override fun displayMedicalCommentDialog(consultationChatVO: ConsultationChatVO) {
        val view = layoutInflater.inflate(R.layout.medical_record_dialog, null)
        val dialog = this?.let { Dialog(it) }
        val pname = view?.findViewById<TextView>(R.id.pname)
        val pdateofBirth = view?.findViewById<TextView>(R.id.pdateofBirth)
        val medical_comment = view?.findViewById<TextView>(R.id.pmedical_comment)
        val p_start_date = view?.findViewById<TextView>(R.id.p_start_date)

        pname?.text = consultationChatVO.patient_info?.name.toString()
        pdateofBirth?.text = consultationChatVO.patient_info?.dateOfBirth.toString()
        p_start_date?.text = consultationChatVO.start_consultation_date
        medical_comment?.text  = consultationChatVO.medical_record.toString()

        dialog?.apply {
            setCancelable(true)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.btn_close.setOnClickListener {
            dialog?.dismiss()
        }
        dialog?.show()
    }

    override fun displayPostponseProcessSuccess() {
        Toast.makeText(this,"ယခု လူနာနှင့် ရက်ချိန်းသတ်မှတ်မှု လုပ်ငန်းစဉ် အောင်မြင်ပါ သည်",Toast.LENGTH_SHORT).show()
    }

}