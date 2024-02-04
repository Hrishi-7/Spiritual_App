package com.hrishikesh.spiritualapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.db.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerName : EditText
    private lateinit var registerEmail : EditText
    private lateinit var registerUsername : EditText
    private lateinit var registerPassword : EditText
    private lateinit var imgRegister : ImageView
    private lateinit var alreadyReg : TextView

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database : FirebaseDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerName = findViewById(R.id.registerName)
        registerEmail = findViewById(R.id.registerEmail)
        registerUsername = findViewById(R.id.registerUsername)
        registerPassword = findViewById(R.id.registerPassword)
        imgRegister = findViewById(R.id.imgRegister)
        alreadyReg = findViewById(R.id.alreadyReg)

        mAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("users")

        alreadyReg.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        imgRegister.setOnClickListener {
            if(checking()){
                val email = registerEmail.text.toString()
                val password = registerPassword.text.toString()
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){

                        val newUser = User(registerName.text.toString(),
                        registerEmail.text.toString(),
                        registerUsername.text.toString(),
                        registerPassword.text.toString())

                        val newUserRef = userRef.push()
                        val userId = newUserRef.key

                        userId?.let {
                            newUserRef.child("name").setValue(newUser.name)
                            newUserRef.child("email").setValue(newUser.email)
                            newUserRef.child("phone").setValue(newUser.phoneNumber)
                            newUserRef.child("password").setValue(newUser.password)
                        }

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "User Created", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "User Not Created", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checking() : Boolean {
        if(registerName.text.isNotEmpty() && registerEmail.text.isNotEmpty()
            && registerUsername.text.isNotEmpty() && registerPassword.text.isNotEmpty()){
            return true
        }
        return false
    }
}