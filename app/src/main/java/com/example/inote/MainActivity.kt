package com.example.inote


import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var viewModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val recyclerview = findViewById<RecyclerView>(R.id.recycler)
        recyclerview.layoutManager =LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerview.adapter = adapter
        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance((application))).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }

        })
        
    }
    fun onItemClicked(note: Note){
        val text = "Bye Note!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        viewModel.deleteNode(note)
    }

    fun submitData(view: View) {
        val noteText=findViewById<EditText>(R.id.input).text.toString()
        if(noteText.isNotEmpty()){
            val text = "Hello Note!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            viewModel.insertNode(Note(noteText))
        }
    }

}