package mk.monthlytut.doctor.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.patient_info_dialog.view.btn_close
import kotlinx.android.synthetic.main.prescription_info_dialog.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.PrescriptionInfoAdapter
import mk.monthlytut.doctor.mvp.presenters.PrescriptionInfoPresenter
import mk.monthlytut.doctor.mvp.presenters.impl.PrescriptionInfoPresenterImpl
import mk.monthlytut.doctor.mvp.views.PrescriptionInfoView
import mk.padc.share.activities.BaseDialogFragment
import mk.padc.share.data.vos.PrescriptionVO

class PrescriptionDialog : BaseDialogFragment() , PrescriptionInfoView {

    private lateinit var mPresenter: PrescriptionInfoPresenter

    private lateinit var adapter: PrescriptionInfoAdapter

    companion object {

        private const val KEY_Chat_id = "KEY_Chat_id"
        private const val KEY_Patient_name ="Key_patient_name"
        private const val KEY_START_DATE ="KEY_START_DATE"

        fun newInstance(consultation_chat_id : String, patient_name : String, startDate : String): PrescriptionDialog {
            val args = Bundle()
            args.putString(KEY_Chat_id, consultation_chat_id)
            args.putString(KEY_Patient_name, patient_name)
            args.putString(KEY_START_DATE, startDate)
            val fragment = PrescriptionDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.prescription_info_dialog, container, false)
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

        var chat_id = arguments?.getString(KEY_Chat_id)
        var name = arguments?.getString(KEY_Patient_name)
        var sdate = arguments?.getString(KEY_START_DATE)

          view.pname.text  = name
          view.pstartdate.text = sdate

          view.rc_medicinelist?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

          mPresenter = getPresenter<PrescriptionInfoPresenterImpl, PrescriptionInfoView>()
          context?.let { mPresenter.onUiReady(it,this) }
          mPresenter.onUiReadyForPrescription(consulation_chat_id = chat_id.toString())
          adapter = PrescriptionInfoAdapter(mPresenter)
          view.rc_medicinelist?.adapter = adapter
          view.rc_medicinelist?.setHasFixedSize(false)

    }

    private fun setupClickListeners(view: View) {
        view.btn_close.setOnClickListener {
            dismiss()
        }
    }

    override fun displayPrescriptionList(prescription_list: List<PrescriptionVO>) {
        adapter.setNewData(prescription_list.toMutableList())
    }

}