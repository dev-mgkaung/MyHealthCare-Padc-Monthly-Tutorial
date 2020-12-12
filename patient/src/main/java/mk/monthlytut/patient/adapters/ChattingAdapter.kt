package mk.monthlytut.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.patient.R
import mk.monthlytut.patient.delegates.ChatRoomDelegate
import mk.monthlytut.patient.views.viewholders.BaseChatViewHolder
import mk.monthlytut.patient.views.viewholders.ConsultationChatDoctorViewHolder
import mk.monthlytut.patient.views.viewholders.ConsultationChatPatientViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.utils.patients

class ChattingAdapter(delegate : ChatRoomDelegate) : BaseRecyclerAdapter<BaseChatViewHolder, ChatMessageVO>()
{
    val mDelegate: ChatRoomDelegate =delegate

    val view_Type_Patient = 1
    val view_Type_Doctor =2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseChatViewHolder {
        when(viewType) {
            view_Type_Patient -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.listitem_chat_patient, parent, false)

                return ConsultationChatPatientViewHolder(view, mDelegate)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.listitem_chat_doctor, parent, false)

                return ConsultationChatDoctorViewHolder(view, mDelegate)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        when{
            mData[position].type == patients ->{
                return view_Type_Patient
            }else->{
            return view_Type_Doctor
        }
        }
    }

}