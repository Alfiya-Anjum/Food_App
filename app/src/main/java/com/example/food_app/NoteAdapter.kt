package com.example.food_app

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val notes: MutableList<String>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvNote)
        val deleteButton: Button = view.findViewById(R.id.btnDelete)
        val checkBox: CheckBox = view.findViewById(R.id.checkboxNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.textView.text = note
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)  // Call the lambda function passed during adapter initialization
        }
        holder.checkBox.setOnCheckedChangeListener(null) // Clear previous listeners
        holder.checkBox.isChecked = false  // Reset checked state when reused
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.textView.paintFlags = holder.textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.textView.paintFlags = holder.textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    override fun getItemCount() = notes.size

    // Method to add a note to the list
    fun addNote(note: String) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }

    // Method to remove a note from the list
    fun deleteNote(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    // Method to remove all notes from the list
    fun deleteAll() {
        notes.clear()
        notifyDataSetChanged()
    }
}
