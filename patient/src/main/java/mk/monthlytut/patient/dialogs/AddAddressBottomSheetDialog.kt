package mk.monthlytut.patient.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.add_shipping_bottonsheet.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.util.SessionManager
import mk.padc.share.activities.BaseBottomSheetDialogFragment

class AddAddressBottomSheetDialog : BaseBottomSheetDialogFragment() {

    private lateinit var state : String
    private lateinit var township : String
    private lateinit var address: String
    private lateinit var shippingList : List<String>
    private lateinit var townshiplist : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_shipping_bottonsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        township_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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


        state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                township_spinner?.adapter = mAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



        btn_add.setOnClickListener {
            address= "${ed_address.text }  ၊ ${township} ၊ ${state} "
            var patientVO = SessionManager.getPatientInfo()
            patientVO.address.add(address)
           // mPresenter.onTapAddShipping(patientVO)

            dismiss()
        }
        shipping_cancel.setOnClickListener {
          dismiss()
        }

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


}