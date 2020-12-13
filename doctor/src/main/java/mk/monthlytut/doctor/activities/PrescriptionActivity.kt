package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_prescription.*
import kotlinx.android.synthetic.main.activity_question_template.*
import kotlinx.android.synthetic.main.activity_question_template.rc_general_questions
import kotlinx.android.synthetic.main.activity_question_template.tex_back
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.MedicalAdapter
import mk.monthlytut.doctor.adapters.QuestionTemplateAdapter
import mk.monthlytut.doctor.mvp.presenters.GeneralQuestionTemplatePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.PrescriptionPresenterImpl
import mk.monthlytut.doctor.mvp.views.PrescriptionView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.MedicineVO

class PrescriptionActivity : BaseActivity() ,PrescriptionView
{
    private lateinit var mPresenter: PrescriptionPresenterImpl
    private lateinit var adapter: MedicalAdapter
    private lateinit var speciality : String

    companion object {
        const val PARM_SPECIALITY = "speciality"

        fun newIntent(context: Context ,speciality : String) : Intent {
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra(PARM_SPECIALITY, speciality)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        tex_back.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpPresenter() {
        mPresenter = getPresenter<PrescriptionPresenterImpl, PrescriptionView>()
        mPresenter.onUiReady(this,this)
        speciality = intent.getStringExtra(PARM_SPECIALITY).toString()
        speciality?.let {
            mPresenter.onUiReadyForPrescription(speciality)
             }
    }

    override fun displayMedicineList(list: List<MedicineVO>) {
        adapter.setNewData(list.toMutableList())
    }

    override fun finishConsulation() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setUpRecyclerView() {
        medical_recyclerview?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MedicalAdapter(mPresenter)
        medical_recyclerview?.adapter = adapter
        medical_recyclerview?.setHasFixedSize(false)
    }

}