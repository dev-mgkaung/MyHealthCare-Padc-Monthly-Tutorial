package mk.padc.share.utils

const val BASE_URL ="https://fcm.googleapis.com/"
const val APIKEY = "key=AAAAwY1etuk:APA91bFRZvX8dkhb2lZP0T_pwSekEmgvtM-Mh5yKriUMvh6tEmHpYb6XRLarLYHZyf-V6bCi5qN0yScR9p-qMARMnihpOlrwDDqURGEo5yhNqUg3rAn5GN3Wi6BvhvHiBvLs_mWKJB0H"
const val DATABASE_NAME="MyCare.DB"

// cloud firebase  Root node name
const val patients = "patients"
const val doctors ="doctors"
const val specialities ="specialities"
const val  general_question_template ="general_question_template"
const val consultation_request ="consultation_request"
const val consultation_chat = "consultation_chat"
const val checkout = "checkout"

// cloud firebase sub collection node name
const val special_questions= "special_questions"
const val medicine = "medicines"
const val recent_doctors ="recent_doctors"
const val general_questions ="general_questions"
const val question_answer = "question_answer"
const val chat_message = "chat_message"
const val prescription = "prescription"
const val delivery_address = "delivery_address"
const val consulted_patient ="consulted_patient"

// type name
const val oneTime ="oneTime"
const val always = "always"
const val patient_id= "patient_id"

//Share Perfrenence for patient
const val sharePreferencePatient  = "patient"
const val sharePreferenceLoginStatus  = "login"
const val sharePreferencePatientName  = "name"
const val sharePreferencePatientPhone  = "phone"
const val sharePreferencePatientAddress  = "address"
const val sharePreferencePatientPERMMENTAddress  = "perment"
const val sharePreferencePatientEmail  = "email"
const val sharePreferencePatientID  = "id"
const val sharePreferencePatientDeviceID  = "device_id"
const val sharePreferencePatientDateOfBirth  = "dateofBirth"
const val sharePreferencePatientHeight  = "height"
const val sharePreferencePatientBloodType = "bloodType"
const val sharePreferencePatientComment  = "comment"
const val sharePreferencePatientBodyWeight  = "weight"
const val sharePreferencePatientBloodPressure  = "blod_pressure"
const val sharePreferencePatientPhoto  = "photo"


//Share Perfernence for doctor
const val sharePreferenceDoctor  = "doctor"
const val sharePreferenceDocotrLoginStatus  = "login_status"
const val sharePreferenceDoctortName  = "doctor_name"
const val sharePreferenceDoctorEmail  = "doctor_email"
const val sharePreferenceDoctorID  = "doctor_id"
const val sharePreferenceDoctorDeviceID  = "doctor_device_id"
const val sharePreferenceDoctorPhoto  = "doctor_photo"
const val sharePreferenceDoctorPhone  = "doctor_phone"
const val sharePreferenceDoctorSpeciality  = "doctor_speciality"
const val sharePreferenceDoctorSpecialityName = "doctor_speciality_name"
const val sharePreferenceDoctorDegree  = "doctor_degree"
const val sharePreferenceDoctorBiography  = "doctor_biography"
const val sharePreferenceDoctorGENDER  = "doctor_gender"
const val sharePreferenceDoctorEXPERIENCE  = "doctor_experience"
const val sharePreferenceDoctorDATEOFBIRTH  = "doctor_dateofbirth"
const val sharePreferenceDoctorAdress = "doctor_address"