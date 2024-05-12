package com.example.crud_application.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.crud_application.R
import com.example.crud_application.database.Note
import com.example.crud_application.databinding.FragmentManageNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Date


class ManageNote : BottomSheetDialogFragment() {

    private var _binding : FragmentManageNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener : OnItemClickListener
    private var noteData :Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManageNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener{
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDescription.text.toString()
            val priority = binding.edtPriority.text.toString()
            val deadline = binding.edtDeadline.text.toString()
            val currentTime = Date()
            var note = Note(0,title,description,priority,deadline,currentTime.toString())

            if(noteData != null){
                note.id =noteData!!.id
                note.date=noteData!!.date
            }
            listener.onItemClick(note)
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        if (noteData != null){
            binding.edtTitle.setText(noteData?.title.toString())
            binding.edtDescription.setText(noteData?.description.toString())
            binding.edtPriority.setText(noteData?.priority.toString())
            binding.edtDeadline.setText(noteData?.deadline.toString())
        } else {
            binding.edtTitle.setText("")
            binding.edtDescription.setText("")
            binding.edtPriority.setText("")
            binding.edtDeadline.setText("")
        }
    }

    fun setNote(dataNote: Note?){
        noteData = dataNote
    }

    interface OnItemClickListener{
        fun onItemClick(note: Note)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}