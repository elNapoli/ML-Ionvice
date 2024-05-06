package com.baldomeronapoli.mlinvoice.data.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthManager @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

}