package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.add_shipping_bottonsheet.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.CheckoutAdpater
import mk.monthlytut.patient.adapters.ShippingAddressAdapter
import mk.monthlytut.patient.dialogs.CheckOutDialog
import mk.monthlytut.patient.mvp.presenters.CheckoutPresenter
import mk.monthlytut.patient.mvp.presenters.impl.CheckoutPresenterImpl
import mk.monthlytut.patient.mvp.views.CheckOutView
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.PrescriptionVO


class CheckoutActivity : BaseActivity(), CheckOutView {

    private lateinit var mPresenter: CheckoutPresenter
    private lateinit var adapter: CheckoutAdpater
    private lateinit var shippingAdapter: ShippingAddressAdapter
    private lateinit var consultation_chat_id: String
    private lateinit var mConsultationChatVO: ConsultationChatVO
    private lateinit var prescriptionList : List<PrescriptionVO>
    private lateinit var totalPrice : String
    private lateinit var state : String
    private lateinit var township : String
    private lateinit var address: String
    private var previousPosition : Int = -1
    private lateinit var shippingList : List<String>
    private lateinit var townshiplist : List<String>


    companion object {
        const val PARM_CONSULTATION_CHAT_ID = "chat id"
        private const val ConsultationCHAT = "ConsultationCHAT"

        fun newIntent(context: Context, consultation_chat_id: String , consultationChatVO: String) : Intent {
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(PARM_CONSULTATION_CHAT_ID, consultation_chat_id)
            intent.putExtra(ConsultationCHAT, consultationChatVO)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        consultation_chat_id = intent.getStringExtra(PARM_CONSULTATION_CHAT_ID).toString()
        var data = intent?.getStringExtra(ConsultationCHAT)
        mConsultationChatVO = Gson().fromJson(data, ConsultationChatVO::class.java)

        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    private fun setUpActionListeners() {

        checkoutback.setOnClickListener {
            onBackPressed()
        }
        add_address.setOnClickListener {
            showBottomSheetDialog()
        }
        btn_order.setOnClickListener {

            mConsultationChatVO?.let {
                mPresenter.onTapCheckout(prescriotionList = prescriptionList,
                        address,
                        doctorVO = it?.doctor_info,
                        patientVO = it?.patient_info,
                        total_price = totalPrice
                )
            }

        }

    }
    private fun setUpPresenter()
    {
        mPresenter = getPresenter<CheckoutPresenterImpl, CheckOutView>()
        mPresenter.onUiReady(this, this)
        mPresenter.onUiReadyCheckout(consultation_chat_id ,this)
    }

    override fun displayPrescription(list: List<PrescriptionVO>) {
        if(list.isNotEmpty()) {
            prescriptionList= list
            adapter.setNewData(list.toMutableList())
            var totalamount : Int =0
            for( item in list)
            {
                totalamount += item.price.toInt()
            }
            total_amount.text = "${totalamount} ကျပ်"
            totalPrice =  "${totalamount} ကျပ်"

        }
    }

    override fun displayShippingAddress(list: List<String>) {
        if(list.isNotEmpty())
        {
             shippingList= list
              shippingAdapter.setNewData(list.toMutableList())
        }
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.add_shipping_bottonsheet, null)
        dialog.setContentView(view)

        view.township_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                township = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        view.state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                state = parent.getItemAtPosition(position).toString()

                if(state == "ရန်ကုန်") {
                    townshiplist = resources.getStringArray(R.array.yangon).toList()
                }else if(state == "နေပြည်တော်")
                {
                    townshiplist = resources.getStringArray(R.array.naypyitaw).toList()
                }else if(state == "ပဲခူး")
                {
                    townshiplist = resources.getStringArray(R.array.bago).toList()
                }else if(state == "မန္တလေး")
                {
                    townshiplist = resources.getStringArray(R.array.mandalay).toList()
                }
                 val mAdapter = ArrayAdapter(view.context,android.R.layout.simple_spinner_dropdown_item,townshiplist)
          //      townshipSpinner?.adapter = mAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.btn_add.setOnClickListener {
            address= "${view.ed_address.text }  ၊ ${township} ၊ ${state} "
            var patientVO = SessionManager.getPatientInfo()
            patientVO.address.add(address)
            mPresenter.onTapAddShipping(patientVO)
            dialog.dismiss()
        }
        view.shipping_cancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    override fun displayConfirmDialog(list: List<PrescriptionVO>, address: String, total_price: String) {
        var data=  Gson().toJson(prescriptionList)
        val dialog: CheckOutDialog = CheckOutDialog.newInstance(data,address,total_price)
        dialog.show(supportFragmentManager, "")
    }


    override fun selectedShippingAddress(mAddress: String,mpreviousPosition : Int) {

        address= mAddress
        btn_order.visibility = View.VISIBLE
        previousPosition = mpreviousPosition
        shippingAdapter = ShippingAddressAdapter(mPresenter, previousPosition)
        shippingList?.let {
            shippingAdapter.setNewData(shippingList.toMutableList())
            address_rc?.adapter = shippingAdapter
        }

    }


    private fun setUpRecyclerView() {
        prescription_rct?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CheckoutAdpater(mPresenter)
        prescription_rct?.adapter = adapter
        prescription_rct?.setHasFixedSize(false)

        address_rc?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        shippingAdapter = ShippingAddressAdapter(mPresenter, previousPosition)
        address_rc?.adapter = shippingAdapter
        address_rc?.setHasFixedSize(false)

    }

    }