package mk.monthlytut.doctor.dialogs

import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.patient_info_dialog.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.QuestionAnswerAdapter
import mk.monthlytut.doctor.mvp.presenters.ChatRoomPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.ChatRoomPresenterImpl
import mk.monthlytut.doctor.mvp.views.ChatView
import mk.padc.share.activities.BaseDialogFragment
import mk.padc.share.data.vos.ConsultationChatVO

class PatientInfoDialog : BaseDialogFragment()  {

    private lateinit var mPresenter: ChatRoomPresenter

    companion object {

        private const val KEY_CONSULATIONCHATVO = "cousulationChatVO"


        fun newInstance(cousulationChatVO: String): PatientInfoDialog {
            val args = Bundle()
            args.putString(KEY_CONSULATIONCHATVO, cousulationChatVO)
            val fragment = PatientInfoDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.patient_info_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.apply {
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun setupView(view: View) {
        var data = arguments?.getString(KEY_CONSULATIONCHATVO)
        val gson = Gson()
        var consultationChatVO = gson.fromJson(data, ConsultationChatVO::class.java)

        view.pname.text = " : " + consultationChatVO.patient_info?.name
        view.pdateofBirth.text =  " : " +consultationChatVO.patient_info?.dateOfBirth
        view.pheight.text =  " : " + consultationChatVO.patient_info?.height
        view.pbloodtype.text = " : " + consultationChatVO.patient_info?.blood_type
        view.pweight.text =  " : " +consultationChatVO.patient_info?.weight
        view.pbloodpressure.text =  " : " +consultationChatVO.patient_info?.blood_pressure
        view.pcomment.text =  " : " + consultationChatVO.patient_info?.comment

        view.rc_question_answer?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mPresenter = getPresenter<ChatRoomPresenterImpl, ChatView>()
        var questionAnswerAdapter = QuestionAnswerAdapter(mPresenter, "")
        view.rc_question_answer?.adapter = questionAnswerAdapter
        view.rc_question_answer?.setHasFixedSize(false)

        consultationChatVO.case_summary?.let{
            questionAnswerAdapter.setNewData(it)
        }
    }

    private fun setupClickListeners(view: View) {
        view.btn_close.setOnClickListener {
            dismiss()
        }
    }

}