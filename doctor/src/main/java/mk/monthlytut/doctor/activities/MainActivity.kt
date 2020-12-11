package mk.monthlytut.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.postpone_dialog.view.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.ConsultationAcceptAdapter
import mk.monthlytut.doctor.adapters.ConsultationRequestAdapter
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.HomePresenterImpl
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.ConsultedPatientVO
import mk.padc.share.utils.ImageUtils

class MainActivity : BaseActivity() ,HomeView {

    private lateinit var mPresenter: HomePresenter

    private lateinit var consultationRequestAdapter:  ConsultationRequestAdapter
    private lateinit var consultationAcceptAdapter:  ConsultationAcceptAdapter


    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drname.text= SessionManager.doctor_name
        ImageUtils().showImage(img_doctor,SessionManager.doctor_photo.toString(),R.drawable.doctor_thumbnail)

        setUpPresenter()
        setUpActionListeners()
        setUpRecyclerView()
    }

    private fun setUpPresenter()
    {
            mPresenter = getPresenter<HomePresenterImpl, HomeView>()
            mPresenter.onUiReady(this,this)
    }

    private fun setUpActionListeners()
    {
        img_doctor.setOnClickListener {
            startActivity(ProfileActivity.newIntent(this))
        }
    }
    private fun setUpRecyclerView()
    {
        rc_consulation_request.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        consultationRequestAdapter = ConsultationRequestAdapter (mPresenter)
        rc_consulation_request.adapter = consultationRequestAdapter

        rc_consulation_accept.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        consultationAcceptAdapter = ConsultationAcceptAdapter (mPresenter)
        rc_consulation_accept.adapter = consultationAcceptAdapter
    }

    override fun displayConsultationRequests(list: List<ConsultationRequestVO>) {
        consultationRequestAdapter.setNewData(list.toMutableList())


    }

    override fun displayConsultationAcceptList(list: List<ConsultationRequestVO>) {
        consultationlabel.visibility = View.VISIBLE
        consultationAcceptAdapter.setNewData(list.toMutableList())
    }

    override fun displayConsultedPatient(list: List<ConsultedPatientVO>) {
        consultationRequestAdapter.setConsultedPatientList(list.toMutableList())
    }

    override fun nextPage(data: ConsultationRequestVO) {
        if(data.consultation_id.toString().isEmpty()) {
            startActivity(data.consultation_id?.let { PatientInfoActivity.newIntent(this, it) })
        }else
        {
            startActivity(data.consultation_id?.let { ChatRoomActvity.newIntent(this, it) })
        }
    }

    override fun displayPostPoneChooserDialog(consultationRequestVO: ConsultationRequestVO) {
        val view = layoutInflater.inflate(R.layout.postpone_dialog, null)
        val dialog = this?.let { Dialog(it) }
        val timePicker = view?.findViewById<TimePicker>(R.id.timePicker)
        var msg : String =""
        timePicker?.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }

                val h = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                 msg = " $h : $min $am_pm"

        }
        dialog?.apply {
            setCancelable(false)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.confirm.setOnClickListener {
            msg?.let{mPresenter.onTapPostponeTime(it,consultationRequestVO)}
            dialog?.dismiss()
        }
        dialog?.show()
    }

}