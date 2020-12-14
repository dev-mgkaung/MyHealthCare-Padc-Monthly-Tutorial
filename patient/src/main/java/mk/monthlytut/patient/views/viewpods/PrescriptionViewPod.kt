package mk.monthlytut.patient.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.prescription_item_for_chat.view.*
import mk.monthlytut.patient.R
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
        ImageUtils().showImage(pdoctor_photo,doctorPhoto, R.drawable.user)
        var str : String = ""
        if(prescription.isNotEmpty())
        {
            for( item in prescription)
            {
                str += item.medicine +"\n"
            }
        }
        txt_medicineList.text = str.toString()
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    private fun setUpListener() {
        btn_prescription.setOnClickListener {
            mDelegate?.onTapPrescription()
        }

    }

    interface Delegate {
        fun onTapPrescription()
    }

}