package mk.padc.share.networks.auth

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

object FirebaseAuthManager : AuthManager {

    private val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override  fun updateProfile(
            photoUrl : String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )
    {
        mFirebaseAuth.currentUser?.updateProfile(UserProfileChangeRequest.Builder().
        setPhotoUri( Uri.parse(photoUrl)).build())?.addOnCompleteListener {
            task -> if(task.isSuccessful)
                    {   onSuccess() } else{  onFailure("Fail Profile Update")}
        }
    }

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete){
                onSuccess()
            } else {
                onFailure(it.exception?.message ?: "Please Check Internet Connection")
            }
        }
    }

    override fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                )
                onSuccess()
            } else {
                onFailure(it.exception?.message ?: "Please check internet connection")
            }
        }
    }

}