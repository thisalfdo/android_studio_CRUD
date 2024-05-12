package com.example.crud_application.adopter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_application.R
import com.example.crud_application.database.Note
import com.example.crud_application.databinding.ItemNoteBinding
import com.example.crud_application.helper.NoteDiffCallback

class NoteAdapter(private val listener: NoteItemClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()

    interface NoteItemClickListener {
        fun onItemClick(note: Note)
        fun onUpdateClick(note: Note)
        fun onDeleteClick(note: Note)
    }

    class NoteViewHolder(private val binding: ItemNoteBinding, private val listener: NoteItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = "Title: ${note.title}"
                tvItemDescription.text = "Description: ${note.description}"
                tvItemPriority.text = "Priority: ${note.priority}"
                tvItemDeadline.text = "Deadline: ${note.deadline}"
                tvItemDate.text = "Date: ${note.date}"


                val priorityColor = note.priority?.let {
                    getPriorityColor(it, itemView.context)
                } ?: ContextCompat.getColor(itemView.context, R.color.default_color)
                cardView.setCardBackgroundColor(priorityColor)

                root.setOnClickListener {
                    listener.onItemClick(note)
                }

                ivUpdate.setOnClickListener {
                    listener.onUpdateClick(note)
                }

                ivDelete.setOnClickListener {
                    listener.onDeleteClick(note)
                }
            }
        }

        private fun getPriorityColor(priority: String, context: Context): Int {
            return when (priority) {
                "High Priority" -> R.color.high_priority_color
                "Medium Priority" -> R.color.medium_priority_color
                "Low Priority" -> R.color.low_priority_color
                else -> R.color.default_color
            }.let { ContextCompat.getColor(context, it) }
        }
    }

    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }
}
