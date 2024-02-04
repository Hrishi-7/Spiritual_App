package com.hrishikesh.spiritualapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.hrishikesh.spiritualapp.R

class LoginActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 120
    }

    private lateinit var loginEmail : EditText
    private lateinit var loginPassword : EditText
    private lateinit var imgLogin : ImageView
    private lateinit var googleBtn : ImageView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        imgLogin = findViewById(R.id.imgLogin)
        googleBtn = findViewById(R.id.googleBtn)

        mAuth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleBtn.setOnClickListener {
            signIn()
        }

        imgLogin.setOnClickListener {
            if(checking()){
                val email = loginEmail.text.toString()
                val password = loginPassword.text.toString()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Signing Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please fill the details", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try{
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                }catch (e : ApiException){
                    e.printStackTrace()
                }
            }else{
                Log.d("SignIn Activity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { it->
                if(it.isSuccessful){
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "log in  failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun checking(): Boolean {
        if(loginEmail.text.isNotEmpty() && loginPassword.text.isNotEmpty()){
            return true
        }
        return false
    }
}