package mk.monthlytut.doctor.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.prescription_item_for_chat_viewpod.view.*
import mk.monthlytut.doctor.R
import mk.padc.share.data.vos.PrescriptionVO
import mk.padc.share.utils.ImageUtils


class PrescriptionViewPod @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpListener()

    }

    fun setPrescriptionData(prescription : List<PrescriptionVO>, doctorPhoto: String) {
       ImageUtils().showImage(ddoctor_photo,doctorPhoto, R.drawable.user)
        var str : String = ""
        if(prescription.isNotEmpty())
        {
            for( item in prescription)
            {
                str += item.medicine +"\n"
            }
        }
        dtxt_medicineList.text = str.toString()
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    private fun setUpListener() {

    }

    interface Delegate {
        fun onTapPrescription()
    }

}