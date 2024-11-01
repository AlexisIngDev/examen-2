package com.example.parras_dev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var logoutButton: Button
    private lateinit var userDetailsTextView: TextView
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        // Set up UI elements
        logoutButton = findViewById(R.id.logout)
        userDetailsTextView = findViewById(R.id.user_details)

        // Check if the user is logged in
        if (user == null) {
            redirectToLogin()
        } else {
            displayUserDetails()
        }

        // Set logout button action
        logoutButton.setOnClickListener {
            signOutUser()
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun displayUserDetails() {
        userDetailsTextView.text = user?.email ?: "Usuario desconocido"
    }

    private fun signOutUser() {
        auth.signOut()
        redirectToLogin()
    }
}
