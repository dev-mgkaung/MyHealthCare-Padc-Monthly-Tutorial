package mk.monthlytut.doctor.utils

import android.content.Context
import android.content.SharedPreferences
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.utils.*

object SessionManager {

    private const val NAME = sharePreferenceDoctor
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
        get() = preferences.getBoolean(sharePreferenceDocotrLoginStatus, false)
        set(value) = preferences.edit {
            it.putBoolean(sharePreferenceDocotrLoginStatus, value)
        }

    var doctor_name: String?

        get() = preferences.getString(sharePreferenceDoctortName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctortName, value)
        }

    var doctor_email: String?

        get() = preferences.getString(sharePreferenceDoctorEmail, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorEmail, value)
        }

    var doctor_id: String?

        get() = preferences.getString(sharePreferenceDoctorID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorID, value)
        }

    var doctor_device_id: String?

        get() = preferences.getString(sharePreferenceDoctorDeviceID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorDeviceID, value)
        }

    var doctor_degree: String?

        get() = preferences.getString(sharePreferenceDoctorDegree, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorDegree, value)
        }

    var doctor_bigraphy: String?

        get() = preferences.getString(sharePreferenceDoctorBiography, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorBiography, value)
        }

    var doctor_photo : String?

        get() = preferences.getString(sharePreferenceDoctorPhoto, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorPhoto, value)
        }

    var doctor_speciality : String?

        get() = preferences.getString(sharePreferenceDoctorSpeciality, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorSpeciality, value)
        }

    var doctor_specialityname : String?

        get() = preferences.getString(sharePreferenceDoctorSpecialityName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorSpecialityName, value)
        }

    var doctor_phone : String?

        get() = preferences.getString(sharePreferenceDoctorPhone, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorPhone, value)
        }


    fun addDoctorInfo(doctorVO: DoctorVO)
    {
        doctor_name = doctorVO.name
        doctor_id = doctorVO.id
        doctor_device_id = doctorVO.device_id
        doctor_email = doctorVO.email.toString()
        doctor_photo = doctorVO.photo.toString()
        doctor_speciality = doctorVO.speciality.toString()
        doctor_specialityname = doctorVO.specialityname.toString()
        doctor_phone = doctorVO.phone
        doctor_degree = doctorVO.degree
        doctor_bigraphy = doctorVO.biography
    }
}