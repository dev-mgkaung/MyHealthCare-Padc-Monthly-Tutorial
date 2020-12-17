package mk.monthlytut.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_checkout.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.CheckoutAdpater
import mk.monthlytut.patient.mvp.presenters.CheckoutPresenter
import mk.monthlytut.patient.mvp.presenters.impl.CheckoutPresenterImpl
import mk.monthlytut.patient.mvp.views.CheckOutView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.PrescriptionVO


class CheckoutActivity : BaseActivity(), CheckOutView {

    private lateinit var mPresenter: CheckoutPresenter
    private lateinit var adapter: CheckoutAdpater
    private lateinit var consultation_chat_id: String

    companion object {
        const val PARM_CONSULTATION_CHAT_ID = "chat id"
        fun newIntent(context: Context, consultation_chat_id: String) : Intent {
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(PARM_CONSULTATION_CHAT_ID, consultation_chat_id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        consultation_chat_id = intent.getStringExtra(PARM_CONSULTATION_CHAT_ID).toString()
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
    }
    private fun setUpPresenter()
    {
        mPresenter = getPresenter<CheckoutPresenterImpl, CheckOutView>()
        mPresenter.onUiReady(this, this)
        mPresenter.onUiReadyCheckout(consultation_chat_id ,this)
    }

    override fun displayPrescription(list: List<PrescriptionVO>) {
        if(list.isNotEmpty()) {
            adapter.setNewData(list.toMutableList())
            var totalamount : Int =0
            for( item in list)
            {
                totalamount += item.price.toInt()
            }
            total_amount.text = "${totalamount} ကျပ်"
        }
    }

    override fun displayShippingAddress(list: List<String>) {
        TODO("Not yet implemented")
    }

    private fun setUpRecyclerView() {
        prescription_rc?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CheckoutAdpater(mPresenter)
        prescription_rc?.adapter = adapter
        prescription_rc?.setHasFixedSize(false)
    }

    }