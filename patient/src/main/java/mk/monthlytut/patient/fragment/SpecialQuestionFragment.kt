package mk.monthlytut.patient.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.case_summary_confirm_dialog.view.*
import kotlinx.android.synthetic.main.fragment_special_question.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.SpecialQuestionAdapter
import mk.monthlytut.patient.delegates.CaseSummaryCallBackListener
import mk.monthlytut.patient.mvp.presenters.CaseSummaryPresenter
import mk.monthlytut.patient.mvp.presenters.impl.CaseSummaryPresenterImpl
import mk.monthlytut.patient.mvp.views.CaseSummaryView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.data.vos.SpecialQuestionVO
import mk.zawuni.zawgyiuni_detect.mmfont.components.MMTextView

private const val ARG_PARAM = "speciality"
class SpecialQuestionFragment : BaseFragment() ,CaseSummaryView{

    lateinit var listener : CaseSummaryCallBackListener

    private lateinit var mPresenter: CaseSummaryPresenter

    private lateinit var adapter: SpecialQuestionAdapter

    private  var questionAnswerList : ArrayList<QuestionAnswerVO> = arrayListOf()

    private var speciality: String? = null

    companion object {

        @JvmStatic
        fun newInstance(mSpeciality : String, listener : CaseSummaryCallBackListener) =
            SpecialQuestionFragment().apply {
                this.listener =listener
                this.speciality=mSpeciality
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_special_question, container, false)
        arguments?.let {
            speciality = it.getString(ARG_PARAM)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListener()
    }

    private fun setUpRecyclerView() {

        rc_specialquestion.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = SpecialQuestionAdapter(mPresenter)
        rc_specialquestion.adapter = adapter
    }

    private fun setUpActionListener() {

        btn_confirm.setOnClickListener {

            listener.onSpecitalQuestionCallBack()
            var patientVO = PatientVO(id= SessionManager.patient_id.toString(),
                device_id = SessionManager.patient_device_id,
                name = SessionManager.patient_name.toString(),
                email = SessionManager.patient_email.toString(),
                photo = SessionManager.patient_photo,
                dateOfBirth = SessionManager.patient_dateOfBirth,
                blood_type = SessionManager.patient_bloodType.toString(),
                blood_pressure = SessionManager.patient_bloodPressure,
                weight = SessionManager.patient_weight,
                height = SessionManager.patient_height,
                comment = SessionManager.patient_comment
            )
            showCaseSummaryConfirmDialog(patientVO)
         }
    }

    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
            mPresenter.onUiReadyforSpecialQuestion(it, speciality.toString(),this)
        }
    }

    override fun displaySpecialQuestions(list: List<SpecialQuestionVO>) {
            adapter.setNewData(list.toMutableList())
            questionAnswerList.clear()
            for(item in list)
            {
            questionAnswerList.add(QuestionAnswerVO(item.id,item.sq_questions,""))
            }
            adapter.setQuestionAnswerList(questionAnswerList)
    }

    override fun displayOnceGeneralQuestion() {}

    override fun displayAlwaysGeneralQuestion() {}

    override fun replaceQuestionAnswerList(position: Int, questionanswervo : QuestionAnswerVO) {
        questionAnswerList.set(position,questionanswervo)
    }

    private  fun showCaseSummaryConfirmDialog(patientVO: PatientVO)
    {
        val view = layoutInflater.inflate(R.layout.case_summary_confirm_dialog, null)
        val dialog = context?.let { Dialog(it) }
        val pdateofBirth = view?.findViewById<TextView>(R.id.pdateofBirth)
        val pname = view?.findViewById<TextView>(R.id.pname)
        val pheight = view?.findViewById<TextView>(R.id.pheight)
        val pbloodtype = view?.findViewById<TextView>(R.id.pbloodtype)
        val pweight = view?.findViewById<TextView>(R.id.pweight)
        val pbloodpressure = view?.findViewById<TextView>(R.id.pbloodpressure)
        val pcomment = view?.findViewById<TextView>(R.id.pcomment)

        pname?.text = patientVO.name
        pdateofBirth?.text = patientVO.dateOfBirth
        pheight?.text = patientVO.height + "ft"
        pbloodtype?.text  =patientVO.blood_type
        pweight?.text  =patientVO.weight + " lb"
        pbloodpressure?.text  =patientVO.blood_pressure + " mmHg"
        pcomment?.text  =patientVO.comment

        dialog?.apply {
            setCancelable(false)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }


        view.cs_btn_confirm.setOnClickListener {
            activity?.let { it -> mPresenter.onTapSendBroadCast(it,speciality.toString(),questionAnswerList,patientVO) }
            dialog?.dismiss()
            activity?.finish()
        }
        dialog?.show()
    }
}