package mk.monthlytut.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_prescription.*
import kotlinx.android.synthetic.main.activity_question_template.tex_back
import kotlinx.android.synthetic.main.routine_dialog.*
import kotlinx.android.synthetic.main.routine_dialog.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.MedicalAdapter
import mk.monthlytut.doctor.mvp.presenters.impl.PrescriptionPresenterImpl
import mk.monthlytut.doctor.mvp.views.PrescriptionView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.MedicineVO
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.data.vos.RoutineVO
import java.util.*
import kotlin.collections.ArrayList

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

    override fun removeMedicine(medicineVO: MedicineVO) {
        var index=0
        for(item in prescriptionList)
        {
            if(item.medicine == medicineVO.name)
            {
                prescriptionList.removeAt(index)
            }
            index++
        }
    }

    override fun finishConsulation() {

    }

    private fun showMedicineDialog(medicineVO: MedicineVO)
    {
        var morningstatus =false
        var afternoonstatus =false
        var nightstatus =false

        var number =1
        var daycount : Int = 0
        var tabcount : String = "1"
        var eatingtime : String =""
        var daystemp : String =""

        val view = layoutInflater.inflate(R.layout.routine_dialog, null)
        val txt_tabcount = view?.findViewById<TextView>(R.id.txt_tabcount)

        val dialog = this?.let { Dialog(it) }

        dialog?.apply {
            setCancelable(true)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.txt_medicine_name.text  =  medicineVO.name
        txt_tabcount?.text =  tabcount

        view.morning.setOnClickListener {
            morningstatus = if(morningstatus) {
                view.morning.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
                view.morning.setTextColor(Color.WHITE)
                false
            }else{
                view.morning.setBackgroundResource(R.drawable.bg_rounded_border_grey)
                view.morning.setTextColor(Color.BLACK)
                true
            }


        }

        view.afternoon.setOnClickListener {
            afternoonstatus = if(afternoonstatus) {
                view.afternoon.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
                view.afternoon.setTextColor(Color.WHITE)
                false
            }else{
                view.afternoon.setBackgroundResource(R.drawable.bg_rounded_border_grey)
                view.afternoon.setTextColor(Color.BLACK)
                true
            }
        }

        view.night.setOnClickListener {
            nightstatus = if(nightstatus) {
                view.night.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
                view.night.setTextColor(Color.WHITE)
                false
            }else{
                view.night.setBackgroundResource(R.drawable.bg_rounded_border_grey)
                view.night.setTextColor(Color.BLACK)
                true
            }
        }

        view.before_eating.setOnClickListener {
            view.before_eating.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
            view.before_eating.setTextColor(Color.WHITE)
            view.after_eating.setBackgroundResource(R.drawable.bg_rounded_border_grey)
            view.after_eating.setTextColor(Color.BLACK)
            eatingtime= "before"
        }

        view.after_eating.setOnClickListener {
            view.after_eating.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
            view.after_eating.setTextColor(Color.WHITE)
            view.before_eating.setBackgroundResource(R.drawable.bg_rounded_border_grey)
            view.before_eating.setTextColor(Color.BLACK)
            eatingtime = "after"
        }

        view.day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                var day = parent.getItemAtPosition(position).toString()
                if(day == "Days")
                {
                    daycount =1
                    daystemp= "Days"
                }else{
                    daycount = 7
                    daystemp="Week"
                }

                    var result = number * daycount
                    txt_tabcount?.text = result.toString()
                    tabcount = result.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.ed_day.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                var data= s.toString()
                if(data.isNotEmpty())
                {
                     number = data.toInt()
                    var result = number * daycount
                    view.txt_tabcount.text = result.toString()
                    tabcount = result.toString()
                }
            }
        })


        view.confirm.setOnClickListener {
            // prescription list add
            var days : String =""
            if(morningstatus)
            {
                days += "#morning"
            }
            if(afternoonstatus)
            {
                days += "#afternoon"
            }
            if(nightstatus)
            {
                days += "#night"
            }

            var medicaltime : String =""

            var routineVO = RoutineVO(
                    id= "0",
                    amount = medicineVO.price.toString(),
                    days = view.ed_day.text.toString() + daystemp,
                    comment = view.pt_comment.text.toString(),
                    quantity = tabcount,
                    times = eatingtime,
                    repeat = days

            )

            var prescriptionVO = PrescriptionVO(
                    id = UUID.randomUUID().toString(),
                    count = tabcount,
                    medicine = medicineVO.name.toString(),
                    price =  medicineVO.price.toString(),
                    routineVO= routineVO
            )
            prescriptionList.add(prescriptionVO)
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