package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_patient_info.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.QuestionAnswerAdapter
import mk.monthlytut.doctor.mvp.presenters.PatientInfoPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.PatientInfoPresenterImpl
import mk.monthlytut.doctor.mvp.views.PatientInfoView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.utils.ImageUtils

class PatientInfoActivity  : BaseActivity()  , PatientInfoView {

    private lateinit var mPresenter: PatientInfoPresenter

    private lateinit var questionAnswerAdapter: QuestionAnswerAdapter

    private lateinit var mConsultationRequestVO: ConsultationRequestVO

    private lateinit var consultation_request_id: String


    override fun displayPatientInfo(consultationRequestVO: ConsultationRequestVO) {
        ImageUtils().showImage(uerimage,consultationRequestVO.patient_info.photo.toString(),R.drawable.user)
        pname.text = " : " + consultationRequestVO.patient_info.name
        pdateofBirth.text =  " : " +consultationRequestVO.patient_info.dateOfBirth
        pheight.text =  " : " + consultationRequestVO.patient_info.height
        pbloodtype.text = " : " + consultationRequestVO.patient_info.blood_type
        pweight.text =  " : " +consultationRequestVO.patient_info.weight
        pbloodpressure.text =  " : " +consultationRequestVO.patient_info.blood_pressure
        pcomment.text =  " : " + consultationRequestVO.patient_info.comment
        mConsultationRequestVO= consultationRequestVO
        questionAnswerAdapter.setNewData(consultationRequestVO.case_summary)
    }

    override fun nextPageToChat(consultation_chat_id: String) {
        if(consultation_chat_id.isNotEmpty()) {
            this.finish()
            Toast.makeText(this,consultation_chat_id+"",Toast.LENGTH_LONG).show()
            startActivity(ChatRoomActvity.newIntent(this, consultation_chat_id))
        }
    }

    companion object {
        const val PARM_CONSULTATION_Request_ID = "consultation_request_id"

        fun newIntent(
            context: Context,
            consultation_chat_id : String
        ) : Intent {
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra(PARM_CONSULTATION_Request_ID, consultation_chat_id)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        consultation_request_id = intent.getStringExtra(PARM_CONSULTATION_Request_ID).toString()

        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }

    private fun setUpPresenter()
    {
        mPresenter = getPresenter<PatientInfoPresenterImpl, PatientInfoView>()
        mPresenter.onUiReadyConstulation(consultation_request_id,this)
    }

    private fun setUpActionListeners()
    {
        cs_btn_confirm.setOnClickListener {
            if(mConsultationRequestVO != null) {
                mPresenter.onTapStartConsultation(mConsultationRequestVO)
            }
        }

        back_patientinfo.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpRecyclerView()
    {
        rc_question_answer?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        questionAnswerAdapter = QuestionAnswerAdapter(mPresenter,"")
        rc_question_answer?.adapter = questionAnswerAdapter
        rc_question_answer?.setHasFixedSize(false)
    }
}