package mk.monthlytut.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.ConsultationAcceptAdapter
import mk.monthlytut.doctor.adapters.ConsultationRequestAdapter
import mk.monthlytut.doctor.mvp.presenters.HomePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.HomePresenterImpl
import mk.monthlytut.doctor.mvp.views.HomeView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.ConsultationRequestVO
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

    override fun nextPage(data: ConsultationRequestVO) {
        if(data.consultation_id.isEmpty()) {
            startActivity(PatientInfoActivity.newIntent(this,data.consultation_id))
        }else
        {
       // chat activity
        }
    }

}