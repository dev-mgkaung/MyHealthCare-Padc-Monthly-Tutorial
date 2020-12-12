package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.utils.doctors


class ChatRoomActvity : BaseActivity() ,ChatView
{

    private lateinit var mPresenter: ChatRoomPresenter

    private lateinit var consultation_chat_id: String
    private lateinit var questionAnswerAdapter: QuestionAnswerAdapter
    private lateinit var mConsultationChatVO: ConsultationChatVO

    private lateinit var adapter: ChattingAdapter

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
        adapter.setNewData(list.toMutableList())
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
            val dialog: PatientInfoDialog = PatientInfoDialog.newInstance(data)
            dialog.show(supportFragmentManager, "")

        }

        btn_attachfile.setOnClickListener {  }

        btn_sendMessage.setOnClickListener {
            mPresenter?.addTextMessage(ed_message.text.toString(),consultation_chat_id, doctors,SessionManager.doctor_photo.toString(),SessionManager.doctor_name.toString(), this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity.newIntent(this))
        this.finish()
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