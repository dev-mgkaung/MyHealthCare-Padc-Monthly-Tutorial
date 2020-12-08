package mk.monthlytut.patient.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.speciality_confrim_dialog.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.activities.CaseSummaryActivity
import mk.monthlytut.patient.adapters.RecentDoctorAdapter
import mk.monthlytut.patient.adapters.SpecialityAdapter
import mk.monthlytut.patient.mvp.presenters.HomePresenter
import mk.monthlytut.patient.mvp.presenters.impl.HomePresenterImpl
import mk.monthlytut.patient.mvp.views.HomeView
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.RecentDoctorVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.zawuni.zawgyiuni_detect.mmfont.components.MMTextView


class HomeFragment : BaseFragment() , HomeView {

    private lateinit var mPresenter: HomePresenter

    // Adapters
    private lateinit var mRecentDoctorAdapter: RecentDoctorAdapter
    private lateinit var mSpecialityAdapter: SpecialityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpActionListener()
        setUpRecyclerView()
    }

    private fun setUpActionListener() {
    }

    private fun setUpPresenter() {

        activity?.let{
            mPresenter = getPresenter<HomePresenterImpl, HomeView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }

    }

    private fun setUpRecyclerView()
    {

      rc_recent_doctor.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
      mRecentDoctorAdapter = RecentDoctorAdapter (mPresenter)
      rc_recent_doctor.adapter = mRecentDoctorAdapter

      rc_speciality.layoutManager = GridLayoutManager(activity ,2)
      mSpecialityAdapter = SpecialityAdapter(mPresenter  )
      rc_speciality.adapter = mSpecialityAdapter

    }
    override fun displayConsultationRequest(consultationRequestVO: ConsultationRequestVO) {
    }

    override fun displayRecentDoctorList(list: List<RecentDoctorVO>) {
        if(list.size > 0) {
            ly_recentdoctor.visibility =View.VISIBLE
            mRecentDoctorAdapter.setNewData(list.toMutableList())
        }else{
            ly_recentdoctor.visibility =View.GONE
        }
    }

    override fun displaySpecialityList(list: List<SpecialitiesVO>) {
        mSpecialityAdapter.setNewData(list.toMutableList())
    }

    override fun nextPageToCaseSummary(specialitiesVO: SpecialitiesVO) {
        val view = layoutInflater.inflate(R.layout.speciality_confrim_dialog, null)
        val dialog = context?.let { Dialog(it) }
        val name = view?.findViewById<MMTextView>(R.id.consultation_request_name_id)
        name?.text = specialitiesVO?.name + resources.getString(R.string.consultation_request_message)

        dialog?.apply {
             setCancelable(false)
             setContentView(view)
             window?.setBackgroundDrawableResource(android.R.color.transparent)
         }

            view.cancel_btn.setOnClickListener {
                dialog?.dismiss()
            }

            view.confirm_btn.setOnClickListener {
                startActivity(  activity?.applicationContext?.let{CaseSummaryActivity.newIntent(it, specialitiesVO.id)})
                dialog?.dismiss()
            }
            dialog?.show()
        }

}