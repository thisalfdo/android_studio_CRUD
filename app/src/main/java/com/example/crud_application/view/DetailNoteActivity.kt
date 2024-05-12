package com.example.crud_application.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_application.database.Note
import com.example.crud_application.databinding.ActivityDetailNoteBinding

class DetailNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataIntent()
    }

    private fun getDataIntent() {
        val dataIntent = intent.extras
        val dataNote = dataIntent?.getParcelable<Note>("notedata")

        binding.tvTitle.text = "Title: ${dataNote?.title}"
        binding.tvDescription.setText("Description:"+ dataNote?.description)
        binding.tvPriority.setText("Priority:"+ dataNote?.priority)
        binding.tvDeadline.setText("Deadline:"+ dataNote?.deadline)
        binding.tvDate.setText("Date:"+dataNote?.date)

        //setToolbar()
    }

//   private fun setToolbar() {
//      setSupportActionBar(binding.toolbar)
//       supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//       binding.toolbar.setNavigationOnClickListener {
//          onBackPressed()
//      }
//   }
}