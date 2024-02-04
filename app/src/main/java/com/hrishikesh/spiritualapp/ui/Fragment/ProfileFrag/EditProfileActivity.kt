package com.hrishikesh.spiritualapp.ui.Fragment.ProfileFrag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.db.UserInfo
import com.hrishikesh.spiritualapp.ui.RegisterActivity

class EditProfileActivity : AppCompatActivity() {
    private lateinit var nameEditProfile : TextInputEditText
    private lateinit var phoneEditProfile : TextInputEditText
    private lateinit var saveBtnEditProfile : Button
    private lateinit var logOutBtnEditProfile : Button

    private lateinit var mAuth : FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        nameEditProfile = findViewById(R.id.nameEditProfile)
        phoneEditProfile = findViewById(R.id.numberEditProfile)
        saveBtnEditProfile = findViewById(R.id.saveBtnEditProfile)
        logOutBtnEditProfile = findViewById(R.id.logOutBtnEditProfile)

        mAuth = Firebase.auth
        database = FirebaseDatabase.getInstance().getReference("userInfo")

        saveBtnEditProfile.setOnClickListener {
            if(checkingInfo()){
                val name = nameEditProfile.text.toString()
                val number = phoneEditProfile.text.toString()
                val uid = mAuth.currentUser?.uid.toString()
                val userInfo = UserInfo(name, number)
                database.child(uid).setValue(userInfo).addOnSuccessListener {
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Not Added", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Enter all the details", Toast.LENGTH_LONG).show()
            }
        }

        logOutBtnEditProfile.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun checkingInfo() : Boolean{
        if(nameEditProfile.text.toString().isEmpty() || phoneEditProfile.text.toString().isEmpty()){
            return false
        }
        return true
    }
}