package com.hrishikesh.spiritualapp.ui.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.db.User
import com.hrishikesh.spiritualapp.db.UserInfo
import com.hrishikesh.spiritualapp.ui.Fragment.ProfileFrag.EditProfileActivity
import com.hrishikesh.spiritualapp.ui.LoginActivity

class ProfileFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var editProfile : Button
    private lateinit var profileName : TextView
    private lateinit var profileNumber : TextView

    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        editProfile = view.findViewById(R.id.editProfile)
        profileName = view.findViewById(R.id.profileName)
        profileNumber = view.findViewById(R.id.profileNumber)

        mAuth = Firebase.auth

        // creating the database reference for the node
        database = FirebaseDatabase.getInstance().reference
        val uid = mAuth.currentUser?.uid.toString()

        // Fetching the node in which the data is stored in the firebase realtime database
        val userRef = database.child("userInfo").child(uid)

        /***
         * Retrieve the data which was stored in the firebase realtime database
         * update the ui after fetching the data from the database
         * the retrieved data is fetched in the form of the data class made in the db package
         */
        userRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val user = snapshot.getValue(UserInfo::class.java)
                    if(user!=null){
                        profileName.text = user.name
                        profileNumber.text = user.number
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        if(mAuth.currentUser?.displayName != null){
            profileName.text = mAuth.currentUser?.displayName.toString()
        }
        if(mAuth.currentUser?.phoneNumber != null){
            profileNumber.text = mAuth.currentUser?.phoneNumber.toString()
        }

        editProfile.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}