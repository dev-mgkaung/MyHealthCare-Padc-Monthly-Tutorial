package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_room.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.ChattingAdapter
import mk.monthlytut.patient.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.patient.mvp.presenters.impl.ChatRoomPresenterImpl
import mk.monthlytut.patient.mvp.views.ChatView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationChatVO

class ChatRoomActvity : BaseActivity() , ChatView
{

    private lateinit var mPresenter: ChatRoomPresenter

    private lateinit var consultation_chat_id: String

    private lateinit var adapter: ChattingAdapter

    companion object {
        const val PARM_CONSULTATION_CHAT_ID = " chat id"
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
        TODO("Not yet implemented")
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
    private fun setUpRecyclerView()
    {
        rc_chating?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ChattingAdapter(mPresenter)
        rc_chating?.adapter = adapter
        rc_chating?.setHasFixedSize(false)
    }
}