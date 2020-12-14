package mk.monthlytut.doctor.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat_room.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.ChattingAdapter
import mk.monthlytut.doctor.adapters.QuestionAnswerAdapter
import mk.monthlytut.doctor.dialogs.PatientInfoDialog
import mk.monthlytut.doctor.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.ChatRoomPresenterImpl
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.monthlytut.doctor.utils.SessionManager
import mk.monthlytut.doctor.views.viewpods.PrescriptionViewPod
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.utils.doctors


class ChatRoomActvity : BaseActivity() ,ChatView
{

    private lateinit var mPresenter: ChatRoomPresenter

    private lateinit var consultation_chat_id: String
    private lateinit var questionAnswerAdapter: QuestionAnswerAdapter
    private lateinit var mConsultationChatVO: ConsultationChatVO

    private lateinit var adapter: ChattingAdapter

    private lateinit var mPrescriptionViewPod : PrescriptionViewPod
    private val QUESTION_TEMPLATE_ACTIVITY_REQUEST_CODE = 0

    companion object {
        const val PARM_CONSULTATION_CHAT_ID = "chat id"
        fun newIntent(
                context: Context,
                consultation_chat_id: String
        ) : Intent {
            val intent = Intent(context, ChatRoomActvity::class.java)
            intent.putExtra(PARM_CONSULTATION_CHAT_ID, consultation_chat_id)
            return intent
        }

    }

    override fun displayPatientInfo(consultationChatVO: ConsultationChatVO) {
        scrollview.scrollTo(0, scrollview.bottom)
        mConsultationChatVO= consultationChatVO
        patientname.text = consultationChatVO.patient_info?.name
        ImageUtils().showImage(userprofile, consultationChatVO.patient_info?.photo.toString(), R.drawable.user)
        pname.text = " : " + consultationChatVO.patient_info?.name
        pdateofBirth.text =  " : " +consultationChatVO.patient_info?.dateOfBirth
        pheight.text =  " : " + consultationChatVO.patient_info?.height
        pbloodtype.text = " : " + consultationChatVO.patient_info?.blood_type
        pweight.text =  " : " +consultationChatVO.patient_info?.weight
        pbloodpressure.text =  " : " +consultationChatVO.patient_info?.blood_pressure
        pcomment.text =  " : " + consultationChatVO.patient_info?.comment
        consultationChatVO.case_summary?.let{
            questionAnswerAdapter.setNewData(it)
        }

    }

    override fun displayChatMessageList(list: List<ChatMessageVO>) {
        scrollview.scrollTo(0, scrollview.getChildAt(0).height)
        adapter.setNewData(list.toMutableList())

    }

    override fun displayPrescriptionViewPod(prescription_list: List<PrescriptionVO>) {
        if(prescription_list.isNotEmpty()) {
            mprescritpionview.visibility = View.VISIBLE
            mPrescriptionViewPod = mprescritpionview as PrescriptionViewPod
            mPrescriptionViewPod.setDelegate(mPresenter)
            mPrescriptionViewPod.setPrescriptionData(prescription_list,mConsultationChatVO.doctor_info?.photo.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        consultation_chat_id = intent.getStringExtra(PARM_CONSULTATION_CHAT_ID).toString()

        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }

    private fun setUpPresenter()
    {
        mPresenter = getPresenter<ChatRoomPresenterImpl, ChatView>()
        mPresenter.onUiReadyConstulation(consultation_chat_id, this)
    }

    private fun setUpActionListeners()
    {
        patientname.setOnClickListener {
            onBackPressed()
        }

        seemore.setOnClickListener {
            var data=  Gson().toJson(mConsultationChatVO)
            mConsultationChatVO?.let {
                val dialog: PatientInfoDialog = PatientInfoDialog.newInstance(data)
                dialog.show(supportFragmentManager, "")
            }
        }

        btn_attachfile.setOnClickListener {
            Toast.makeText(this, this.resources.getString(R.string.image_upload_service_not_available),Toast.LENGTH_LONG).show()
        }

        btn_sendMessage.setOnClickListener {
            if (mConsultationChatVO.finish_consultation_status) {
                Toast.makeText(this,"ဆွေးနွေးမှု ပြီးဆုံးပါပြီ စာပို့လို့မရနိုင်တော့ပါ",Toast.LENGTH_SHORT).show()
            } else {
                if (ed_message.text.toString().isNotEmpty()) {
                    mPresenter?.addTextMessage(ed_message.text.toString(), consultation_chat_id, doctors, SessionManager.doctor_photo.toString(), SessionManager.doctor_name.toString(), this)
                } else {
                    Toast.makeText(this, "Empty text", Toast.LENGTH_SHORT).show()
                }
            }
        }

        question_btn.setOnClickListener {
            val intent = Intent(this, QuestionTemplateActivity::class.java)
            startActivityForResult( intent ,QUESTION_TEMPLATE_ACTIVITY_REQUEST_CODE)
        }

        prescription_btn.setOnClickListener {
            var data=  Gson().toJson(mConsultationChatVO)
            mConsultationChatVO?.let {
                startActivity(PrescriptionActivity.newIntent(this,mConsultationChatVO.doctor_info?.speciality.toString(),data))
            }

        }

        medical_record_btn.setOnClickListener {
            var data=  Gson().toJson(mConsultationChatVO)
            mConsultationChatVO?.let {
                startActivity(MedicalCommentAcitivity.newIntent(this,data))
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity.newIntent(this))
        this.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == QUESTION_TEMPLATE_ACTIVITY_REQUEST_CODE)
        {
            if( resultCode == Activity.RESULT_OK)
            {
                val returnString = data?.getStringExtra("questions")
                ed_message.text = Editable.Factory.getInstance().newEditable(returnString)
            }
        }
    }
    private fun setUpRecyclerView()
    {
        rc_chating?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ChattingAdapter(mPresenter)
        rc_chating?.adapter = adapter
        rc_chating?.setHasFixedSize(false)


        rc_question_answer?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        questionAnswerAdapter = QuestionAnswerAdapter(mPresenter, "chat")
        rc_question_answer?.adapter = questionAnswerAdapter
        rc_question_answer?.setHasFixedSize(false)
    }
}