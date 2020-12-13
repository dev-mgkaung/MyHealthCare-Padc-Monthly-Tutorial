package mk.monthlytut.doctor.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_room.*
import kotlinx.android.synthetic.main.activity_question_template.*
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.adapters.ChattingAdapter
import mk.monthlytut.doctor.adapters.QuestionTemplateAdapter
import mk.monthlytut.doctor.mvp.presenters.GeneralQuestionTemplatePresenter
import mk.monthlytut.doctor.mvp.presenters.impl.GeneralQuestionTemplatePresenterImpl
import mk.monthlytut.doctor.mvp.views.GeneralQuestionTemplateView
import mk.padc.share.activities.BaseActivity
import mk.padc.share.data.vos.GeneralQuestionTemplateVO


class QuestionTemplateActivity : BaseActivity() , GeneralQuestionTemplateView
{

    private lateinit var mPresenter: GeneralQuestionTemplatePresenter
    private lateinit var adapter: QuestionTemplateAdapter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, QuestionTemplateActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_template)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        tex_back.setOnClickListener { onBackPressed() }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<GeneralQuestionTemplatePresenterImpl, GeneralQuestionTemplateView>()
        mPresenter.onUiReady(this,this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        sendDataToPreviousPage("")
    }

    private fun setUpRecyclerView() {
        rc_general_questions?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = QuestionTemplateAdapter(mPresenter)
        rc_general_questions?.adapter = adapter
        rc_general_questions?.setHasFixedSize(false)
    }
        fun sendDataToPreviousPage(questions : String)
    {
        val intent = Intent()
        intent.putExtra("questions" , questions)
        setResult(Activity.RESULT_OK , intent)
        finish()
    }

    override fun displayGeneralQuestions(list: List<GeneralQuestionTemplateVO>) {
        adapter.setNewData(list.toMutableList())
    }

    override fun navigateToToChatRoom(questions: String) {
        sendDataToPreviousPage(questions)
    }
}