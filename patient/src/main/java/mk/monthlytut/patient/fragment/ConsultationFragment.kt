package mk.monthlytut.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_consultation.*
import mk.monthlytut.patient.R
import mk.monthlytut.patient.adapters.ChatAdapter
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
        adapter.setNewData(list.toMutableList())
    }
}