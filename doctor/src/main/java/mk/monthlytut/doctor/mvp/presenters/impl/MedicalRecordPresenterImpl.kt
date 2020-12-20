package mk.monthlytut.doctor.mvp.presenters.impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mk.monthlytut.doctor.mvp.presenters.MedicalRecordPresenter
import mk.monthlytut.doctor.mvp.views.MedicalRecordView
import mk.monthlytut.doctor.utils.SessionManager
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.models.impl.DoctorModelImpl
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.mvp.presenters.AbstractBasePresenter


class MedicalRecordPresenterImpl : MedicalRecordPresenter, AbstractBasePresenter<MedicalRecordView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl

    override fun onTapSaveMedicalRecord(consultationChatVO: ConsultationChatVO, owner: LifecycleOwner) {
        doctorModel.saveMedicalRecord(consultationChatVO,onSuccess = {}, onError = {
            mView?.showSnackBar("ဆေးမှတ်တမ်း မသိမ်းဆည်းနိုင်ပါ")
        })
        mView?.showSnackBar("ဆေးမှတ်တမ်း သိမ်းဆည်းပြီးပါပြီ")
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

}