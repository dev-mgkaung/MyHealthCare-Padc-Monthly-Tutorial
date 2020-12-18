package mk.monthlytut.patient.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.utils.*

object SessionManager {

    private const val NAME = sharePreferencePatient
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var login_status: Boolean

        get() = preferences.getBoolean(sharePreferenceLoginStatus, false)

        set(value) = preferences.edit {
            it.putBoolean(sharePreferenceLoginStatus, value)
        }

    var patient_name: String?

        get() = preferences.getString(sharePreferencePatientName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientName, value)
        }

    var patient_email: String?

        get() = preferences.getString(sharePreferencePatientEmail, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientEmail, value)
        }

    var patient_phone : String?

        get() = preferences.getString(sharePreferencePatientPhone, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPhone, value)
        }

    var patient_address : String?

        get() = preferences.getString(sharePreferencePatientAddress, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientAddress, value)
        }

    var patient_perment_address : String?

        get() = preferences.getString(sharePreferencePatientPERMMENTAddress, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPERMMENTAddress, value)
        }


    var patient_id: String?

        get() = preferences.getString(sharePreferencePatientID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientID, value)
        }

    var patient_device_id: String?

        get() = preferences.getString(sharePreferencePatientDeviceID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDeviceID, value)
        }

    var patient_dateOfBirth: String?

        get() = preferences.getString(sharePreferencePatientDateOfBirth, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDateOfBirth, value)
        }

    var patient_height: String?

        get() = preferences.getString(sharePreferencePatientHeight, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientHeight, value)
        }

    var patient_bloodType: String?

        get() = preferences.getString(sharePreferencePatientBloodType, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBloodType, value)
        }

    var patient_comment : String?

        get() = preferences.getString(sharePreferencePatientComment, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientComment, value)
        }

    var patient_weight : String?

        get() = preferences.getString(sharePreferencePatientBodyWeight, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBodyWeight, value)
        }

    var patient_bloodPressure : String?

        get() = preferences.getString(sharePreferencePatientBloodPressure, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBloodPressure, value)
        }

    var patient_photo : String?

        get() = preferences.getString(sharePreferencePatientPhoto, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPhoto, value)
        }



    fun addPatientInfo( patientVO: PatientVO)
    {

        patient_name = patientVO.name
        patient_id = patientVO.id
        patient_device_id = patientVO.device_id
        patient_email = patientVO.email
        patient_photo = patientVO.photo.toString()
        patient_dateOfBirth =patientVO.dateOfBirth
        patient_height = patientVO.height
        patient_bloodType = patientVO.blood_type
        patient_comment = patientVO.comment
        patient_weight = patientVO.weight
        patient_bloodPressure = patientVO.blood_pressure
        patient_phone = patientVO.phone
        patient_address =patientVO.perment_address
        patient_address =  Gson().toJson(patientVO.address)

    }

    fun getPatientInfo() : PatientVO
    {
        val gson = Gson()
        var addressList = gson.fromJson(SessionManager.patient_address, Array<String>::class.java).toMutableList()

        return  PatientVO(
                 id = SessionManager.patient_id.toString(),
                 device_id = SessionManager.patient_device_id.toString(),
                 name =  SessionManager.patient_name.toString(),
                 email  = SessionManager.patient_email.toString(),
                 photo = SessionManager.patient_photo.toString(),
                 blood_type =SessionManager.patient_bloodType.toString(),
                 blood_pressure=  SessionManager.patient_bloodPressure.toString(),
                 dateOfBirth =  SessionManager.patient_dateOfBirth.toString(),
                 weight =  SessionManager.patient_weight.toString(),
                 height =  SessionManager.patient_height.toString(),
                 comment =  SessionManager.patient_comment.toString(),
                 phone =  SessionManager.patient_phone.toString(),
                 perment_address=  SessionManager.patient_perment_address.toString(),
                 address = addressList as ArrayList<String>
        )
    }
}