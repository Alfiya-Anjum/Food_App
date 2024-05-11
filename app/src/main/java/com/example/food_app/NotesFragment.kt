package com.example.food_app

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class NotesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private lateinit var noteEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        noteEditText = view.findViewById(R.id.etNote)
        val addNoteButton: Button = view.findViewById(R.id.btnAddNote)
        val deleteAllButton: Button = view.findViewById(R.id.btnDeleteAll)
        recyclerView = view.findViewById(R.id.rvNotes)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize the adapter with an empty list and a lambda for handling deletions
        adapter = NoteAdapter(arrayListOf()) { position ->
            adapter.deleteNote(position)
        }
        recyclerView.adapter = adapter

        addNoteButton.setOnClickListener {
            val noteText = noteEditText.text.toString().trim()
            if (noteText.isNotEmpty()) {
                adapter.addNote(noteText)
                noteEditText.setText("")
            }
        }

        deleteAllButton.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete all notes?")
                .setPositiveButton("Yes") { dialog, which -> adapter.deleteAll() }
                .setNegativeButton("No", null)
                .show()
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete_all) {
            AlertDialog.Builder(requireContext()).setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete all notes?")
                .setPositiveButton("Yes") { dialog, which -> adapter.deleteAll() }
                .setNegativeButton("No", null)
                .show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
