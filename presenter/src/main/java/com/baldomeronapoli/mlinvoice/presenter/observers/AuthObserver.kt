package com.baldomeronapoli.mlinvoice.presenter.observers

import com.google.firebase.auth.FirebaseUser

interface Observable {
    fun addObserver(observer: AuthObserver)
    fun removeObserver(observer: AuthObserver)
    fun notifyObservers(action: (AuthObserver) -> Unit)
}

interface AuthObserver {
    fun onSiIn(user: FirebaseUser?)
}