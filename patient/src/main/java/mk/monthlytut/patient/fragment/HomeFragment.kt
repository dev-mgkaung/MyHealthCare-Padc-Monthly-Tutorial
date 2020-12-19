package mk.monthlytut.patient.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.speciality_confrim_dialog.view.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.activities.CaseSummaryActivity
import mk.monthlytut.patient.activities.ChatRoomActvity
import mk.monthlytut.patient.adapters.ConsultationAdapter
import mk.monthlytut.patient.adapters.RecentDoctorAdapter
import mk.monthlytut.patient.adapters.SpecialityAdapter
import mk.monthlytut.patient.mvp.presenters.HomePresenter
import mk.monthlytut.patient.mvp.presenters.impl.HomePresenterImpl
import mk.monthlytut.patient.mvp.views.HomeView
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.RecentDoctorVO
import mk.padc.share.data.vos.SpecialitiesVO
import mk.zawuni.zawgyiuni_detect.mmfont.components.MMTextView


class HomeFragment : BaseFragment() , HomeView {

    private lateinit var mPresenter: HomePresenter

    private lateinit var mRecentDoctorAdapter: RecentDoctorAdapter
    private lateinit var mSpecialityAdapter: SpecialityAdapter
    private lateinit var consultationAdapter : ConsultationAdapter

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
        setUpRecyclerView()
    }

    private fun setUpPresenter() {

        activity?.let{
            mPresenter = getPresenter<HomePresenterImpl, HomeView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }

    }

    private fun setUpRecyclerView()
    {
      rc_consulation.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
      consultationAdapter = ConsultationAdapter (mPresenter)
      rc_consulation.adapter = consultationAdapter

      rc_recent_doctor.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
      mRecentDoctorAdapter = RecentDoctorAdapter (mPresenter)
      rc_recent_doctor.adapter = mRecentDoctorAdapter

      rc_speciality.layoutManager = GridLayoutManager(activity ,2)
      mSpecialityAdapter = SpecialityAdapter(mPresenter  )
      rc_speciality.adapter = mSpecialityAdapter

    }
    override fun displayConsultationRequest(list: List<ConsultationRequestVO>) {
        consultationAdapter.setNewData(list.toMutableList())
    }

    override fun displayRecentDoctorList(list: List<RecentDoctorVO>) {
        if(list.isNotEmpty()) {
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
                var doctorVO = DoctorVO()
                var mdoctorVO=  Gson().toJson(doctorVO)
                dialog?.dismiss()
                activity?.let{
                    startActivity(  activity?.applicationContext?.let{CaseSummaryActivity.newIntent(it, specialitiesVO.id.toString(),mdoctorVO.toString())})
                }

            }
            dialog?.show()
        }

    override fun nextPageToCaseSummaryFromRecentDoctor(recentdoctor: RecentDoctorVO) {
        var doctorVO = DoctorVO(
             id = recentdoctor.id.toString(),
             device_id = recentdoctor.device_id.toString(),
            name = recentdoctor.name.toString(),
            email = recentdoctor.email.toString(),
            phone = recentdoctor.phone.toString(),
            photo = recentdoctor.photo.toString(),
            speciality = recentdoctor.speciality.toString(),
            specialityname = recentdoctor.specialityname.toString(),
            degree = recentdoctor.degree.toString(),
            biography = recentdoctor.biography.toString(),
            dateofBirth =  recentdoctor.dateofBirth.toString(),
            gender = recentdoctor.gender.toString(),
            experience = recentdoctor.experience.toString(),
            address = recentdoctor.address.toString(),
        )
        var mdoctorVO=  Gson().toJson(doctorVO)
        startActivity(  activity?.applicationContext?.let{CaseSummaryActivity.newIntent(it, recentdoctor.speciality.toString(),mdoctorVO.toString())})

    }

    override fun nextPageToChatRoom(consulation_chat_id: String,consultationRequestVO: ConsultationRequestVO) {

        activity?.let{
            mPresenter.statusUpdateForCompleteType(it,consulation_chat_id,consultationRequestVO)
            it.finish()
            it.startActivity(ChatRoomActvity.newIntent(it, consulation_chat_id))
        }
    }

}