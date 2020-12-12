package mk.monthlytut.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import mk.monthlytut.doctor.R
import mk.monthlytut.doctor.delegates.ChatRoomDelegate
import mk.monthlytut.doctor.views.viewholders.BaseChatViewHolder
import mk.monthlytut.doctor.views.viewholders.ConsultationChatDoctorViewHolder
import mk.monthlytut.doctor.views.viewholders.ConsultationChatPatientViewHolder
import mk.padc.share.adapters.BaseRecyclerAdapter
import mk.padc.share.data.vos.ChatMessageVO
import mk.padc.share.utils.patients

class ChattingAdapter(delegate : ChatRoomDelegate) :BaseRecyclerAdapter<BaseChatViewHolder,ChatMessageVO>()
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