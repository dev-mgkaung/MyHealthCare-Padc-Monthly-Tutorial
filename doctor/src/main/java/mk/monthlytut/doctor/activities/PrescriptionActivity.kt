package mk.monthlytut.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_prescription.*
import kotlinx.android.synthetic.main.activity_question_template.tex_back
import kotlinx.android.synthetic.main.routine_dialog.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.MedicalAdapter
import mk.monthlytut.doctor.mvp.presenters.impl.PrescriptionPresenterImpl
import mk.monthlytut.doctor.mvp.views.PrescriptionView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.MedicineVO
import mk.padc.share.data.vos.PrescriptionVO

class PrescriptionActivity : BaseActivity() ,PrescriptionView
{
    private lateinit var mPresenter: PrescriptionPresenterImpl
    private lateinit var adapter: MedicalAdapter
    private lateinit var speciality : String
    private lateinit var list : List<MedicineVO>
    private  var filterlist : ArrayList<MedicineVO> = arrayListOf()
    private  var prescriptionList : ArrayList<PrescriptionVO> = arrayListOf()

    companion object {
        const val PARM_SPECIALITY = "speciality"

        fun newIntent(context: Context ,speciality : String) : Intent {
            val intent = Intent(context, PrescriptionActivity::class.java)
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

        search_medicine.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        tex_back.setOnClickListener {
            onBackPressed()
        }
    }

    fun filter(text : String)
    {
        filterlist.clear()
        list?.let{

            for( item in list)
            {
                var st = item.name.toString().toLowerCase()
                if(st.contains(text))
                {
                    filterlist.add(item)
                }
            }
            adapter.setMedicineList(filterlist)
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
        this.list = list
        adapter.setNewData(list.toMutableList())
    }

    override fun displayRoutinechooseDialog(medicineVO: MedicineVO) {
        showMedicineDialog(medicineVO)
    }

    override fun finishConsulation() {

    }

    private fun showMedicineDialog(medicineVO: MedicineVO)
    {
        val view = layoutInflater.inflate(R.layout.routine_dialog, null)
        val dialog = this?.let { Dialog(it) }
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.apply {
            setCancelable(true)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.confirm.setOnClickListener {
            // prescription list add
            dialog?.dismiss()
        }
        dialog?.show()
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