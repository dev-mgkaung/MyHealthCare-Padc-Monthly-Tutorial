package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_room.*
import kotlinx.android.synthetic.main.activity_chat_room.pbloodpressure
import kotlinx.android.synthetic.main.activity_chat_room.pbloodtype
import kotlinx.android.synthetic.main.activity_chat_room.pcomment
import kotlinx.android.synthetic.main.activity_chat_room.pdateofBirth
import kotlinx.android.synthetic.main.activity_chat_room.pheight
import kotlinx.android.synthetic.main.activity_chat_room.pname
import kotlinx.android.synthetic.main.activity_chat_room.pweight
import kotlinx.android.synthetic.main.activity_chat_room.rc_question_answer
import kotlinx.android.synthetic.main.activity_patient_info.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.ChattingAdapter
import mk.monthlytut.doctor.adapters.QuestionAnswerAdapter
import mk.monthlytut.doctor.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.ChatRoomPresenterImpl
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.utils.ImageUtils

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
            consultation_chat_id : String
        ) : Intent {
            val intent = Intent(context, ChatRoomActvity::class.java)
            intent.putExtra(PARM_CONSULTATION_CHAT_ID, consultation_chat_id)
            return intent
        }

    }

    override fun displayPatientInfo(consultationChatVO: ConsultationChatVO) {
        mConsultationChatVO= consultationChatVO
        patientname.text = consultationChatVO.patient_info?.name
        ImageUtils().showImage(userprofile,consultationChatVO.patient_info?.photo.toString(),R.drawable.user)
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
        mPresenter.onUiReadyConstulation(consultation_chat_id,this)
    }

    private fun setUpActionListeners()
    {
        patientname.setOnClickListener {
            onBackPressed()
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
        questionAnswerAdapter = QuestionAnswerAdapter(mPresenter ,"chat")
        rc_question_answer?.adapter = questionAnswerAdapter
        rc_question_answer?.setHasFixedSize(false)
    }
}