package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat_room.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.ChattingAdapter
import mk.monthlytut.patient.adapters.QuestionAnswerAdapter
import mk.monthlytut.patient.dialogs.PatientInfoDialog
import mk.monthlytut.patient.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.patient.mvp.presenters.impl.ChatRoomPresenterImpl
import mk.monthlytut.patient.mvp.views.ChatView
import mk.monthlytut.patient.util.SessionManager
import mk.monthlytut.patient.views.viewpods.PrescriptionViewPod
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.utils.ImageUtils
import mk.padc.share.utils.patients

class ChatRoomActvity : BaseActivity() , ChatView
{

    private lateinit var mPresenter: ChatRoomPresenter

    private lateinit var consultation_chat_id: String
    private lateinit var questionAnswerAdapter: QuestionAnswerAdapter
    private lateinit var mConsultationChatVO: ConsultationChatVO

    private lateinit var adapter: ChattingAdapter
    var prescription_show =false
    private lateinit var mPrescriptionViewPod : PrescriptionViewPod
    var finish_conservation_status =false

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
        scrollview.scrollTo(0, scrollview.getChildAt(0).height)
        prescription_show =true
        mConsultationChatVO= consultationChatVO
        patientname.text = consultationChatVO.doctor_info?.name
        ImageUtils().showImage(userprofile, consultationChatVO.doctor_info?.photo.toString(), R.drawable.user)
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
        finish_conservation_status = consultationChatVO.finish_consultation_status
        if(finish_conservation_status) {
            prescritpionview.visibility = View.VISIBLE
        }else{
            prescritpionview.visibility = View.GONE
        }
    }

    override fun displayChatMessageList(list: List<ChatMessageVO>) {
        scrollview.scrollTo(0, scrollview.getChildAt(0).height)
        adapter.setNewData(list.toMutableList())
    }

    override fun displayPrescriptionViewPod(prescription_list: List<PrescriptionVO>) {

       if(prescription_list.isNotEmpty()) {

           mPrescriptionViewPod = prescritpionview as PrescriptionViewPod
           mPrescriptionViewPod.setDelegate(mPresenter)

           if(prescription_show)
           {
               mConsultationChatVO?.let {
                   mPrescriptionViewPod.setPrescriptionData(prescription_list,mConsultationChatVO.doctor_info?.photo.toString(), mConsultationChatVO.id.toString())
               }
           }

           if(finish_conservation_status) {
               prescritpionview.visibility = View.VISIBLE
               sendLayoutpatent.visibility =View.GONE
           }else{
               prescritpionview.visibility = View.GONE
               sendLayoutpatent.visibility =View.VISIBLE
           }

          }
    }

    override fun nextPageToCheckout(chatId: String) {
        mConsultationChatVO?.let {
            var data = Gson().toJson(mConsultationChatVO)
            startActivity(CheckoutActivity.newIntent(this,chatId,data))
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
            val dialog: PatientInfoDialog = PatientInfoDialog.newInstance(data)
            dialog.show(supportFragmentManager, "")

        }


        btn_attachfile.setOnClickListener {
            Toast.makeText(this, this.resources.getString(R.string.image_upload_service_not_available),
                Toast.LENGTH_LONG).show()
        }

        btn_sendMessage.setOnClickListener {
            mConsultationChatVO?.let {
                if (mConsultationChatVO.finish_consultation_status) {
                    Toast.makeText(this,"ဆွေးနွေးမှု ပြီးဆုံးပါပြီ စာပို့လို့မရနိုင်တော့ပါ",Toast.LENGTH_SHORT).show()
                } else {
                    if (ed_message.text.toString().isNotEmpty()) {
                    mPresenter?.addTextMessage(ed_message.text.toString(), consultation_chat_id, patients, SessionManager.patient_photo.toString(), SessionManager.patient_name.toString(), this)
                        ed_message.text =  Editable.Factory.getInstance().newEditable("")
                    } else {
                        Toast.makeText(this, "Empty text", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
        startActivity(HomeActivity.newIntent(this))

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