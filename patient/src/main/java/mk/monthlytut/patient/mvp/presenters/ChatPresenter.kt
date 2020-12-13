package mk.monthlytut.patient.mvp.presenters

import mk.monthlytut.patient.delegates.ChatHistoryDelegate
import mk.monthlytut.patient.mvp.views.ChatHistoryView
import mk.padc.share.mvp.presenters.BasePresenter

interface ChatPresenter : BasePresenter<ChatHistoryView>, ChatHistoryDelegate {

}