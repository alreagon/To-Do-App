package com.zexceed.to_doapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.zexceed.to_doapp.R
import com.zexceed.to_doapp.databinding.FragmentAddTodoPopUpBinding

class AddTodoPopUpFragment : DialogFragment() {

    private lateinit var binding: FragmentAddTodoPopUpBinding
    private lateinit var listener: DialogNextBtnClickListener

    fun setListener(listener: DialogNextBtnClickListener){
        this.listener = listener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoPopUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerEvents()

    }

    private fun registerEvents() {

        binding.nextBtnTask.setOnClickListener {
            val todoTask = binding.toDoEt.text.toString()
            if (todoTask.isNotEmpty()){
                listener.onSaveTask(todoTask,binding.toDoEt)

            }else{
                Toast.makeText(context, "Please type some task", Toast.LENGTH_SHORT).show()
            }

        }

        binding.toDoClose.setOnClickListener{
            dismiss()
        }

    }
    //TODO: ini bukan cara terbaik untuk nge fetch data, harusnya di bikin fragment atau activity tertentu
    interface DialogNextBtnClickListener{
        fun onSaveTask(todo : String, todoEt : TextInputEditText)
    }


}