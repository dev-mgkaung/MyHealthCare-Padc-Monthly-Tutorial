package mk.monthlytut.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_consultation.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.activities.ChatRoomActvity
import mk.monthlytut.patient.adapters.ChatAdapter
import mk.monthlytut.patient.dialogs.PrescriptionDialog
import mk.monthlytut.patient.mvp.presenters.ChatPresenter
import mk.monthlytut.patient.mvp.presenters.impl.ChatPresenterImpl
import mk.monthlytut.patient.mvp.views.ChatHistoryView
import mk.padc.share.activities.BaseFragment
import mk.padc.share.data.vos.ConsultationChatVO


class ConsultationFragment : BaseFragment(), ChatHistoryView {

    private lateinit var mPresenter: ChatPresenter

    private lateinit var adapter:  ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListener()
    }
    private fun setUpRecyclerView() {
        rc_chat_history?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = ChatAdapter(mPresenter)
        rc_chat_history?.adapter = adapter
        rc_chat_history?.setHasFixedSize(false)
    }

        private fun setUpActionListener() {}

    private fun setUpPresenter() {
        mPresenter = getPresenter<ChatPresenterImpl, ChatHistoryView>()
        activity?.let { mPresenter.onUiReady(it, this) }
    }

    override fun displayChatHistoryList(list: List<ConsultationChatVO>) {
       if(list.isNotEmpty()) {
           rc_chat_history.visibility = View.VISIBLE
           empty_view.visibility = View.GONE
       }
        else{
           rc_chat_history.visibility = View.GONE
           empty_view.visibility =View.VISIBLE
       }
        adapter.setNewData(list.toMutableList())
    }

    override fun nextPageToChatRoom(consulationchatId: String) {
        activity?.let{
            it.finish()
            startActivity(activity?.let { ChatRoomActvity.newIntent(it, consulationchatId) })
        }

    }

    override fun showPrescriptionDialog(finishconsultation: Boolean,consulationchatId: String, patient_name: String,start_conservation_date : String ) {
         if(finishconsultation)
         {
             val dialog: PrescriptionDialog = PrescriptionDialog.newInstance(consulationchatId, patient_name, start_conservation_date)
             fragmentManager?.let { dialog.show(it, "") }

         }else {
             Toast.makeText(activity,"ဆေးညွန်းမရှိသေးပါ",Toast.LENGTH_SHORT).show()
         }
    }
}