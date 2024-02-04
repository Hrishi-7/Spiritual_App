package com.hrishikesh.spiritualapp.ui.Fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.firebase.storage.FirebaseStorage
import com.hrishikesh.spiritualapp.R
import java.io.File
import java.net.URI


class SavedFragment : Fragment() {
    private lateinit var bookViewBtn : Button
    private lateinit var storage: FirebaseStorage
    private lateinit var loadProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_saved, container, false)

        bookViewBtn = view.findViewById(R.id.bookViewBtn)
        loadProgressBar = view.findViewById(R.id.loadProgressBar)
        storage = FirebaseStorage.getInstance()

        val httpReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/spiritual-app-56c31.appspot.com/o/Bhagavad-gita-hindi.pdf?alt=media&token=277c4796-b8b1-49d4-ba41-c3c0d6e7dfa3")


        bookViewBtn.setText("Bhagwad Gita")

        /***
         * File provider is used to open the pdf from a local pdf viewer
         * From the android 24 onwards sharing the files to another app is restricted and some
         * boundaries are made to do so, therefore the file provider is used to open the pdf
         * File provider enhancements are made in the manifest.xml file and provider_path.xml is made
         */
        bookViewBtn.setOnClickListener {
            loadProgressBar.visibility = View.VISIBLE
            val localFile = File.createTempFile("document", ".pdf")
            httpReference.getFile(localFile).addOnSuccessListener {
                val intent = Intent(Intent.ACTION_VIEW)
                val uri : Uri? = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", localFile)
                intent.setDataAndType(uri, "application/pdf")
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                try{
                    startActivity(intent)
                }catch (e:ActivityNotFoundException){
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed to download the pdf, Try again!!", Toast.LENGTH_LONG).show()
                }finally {
                    loadProgressBar.visibility = View.INVISIBLE
                }
            }.addOnFailureListener{
                Toast.makeText(activity, "File not found", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }


}