package com.task.noteapp.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentEditNoteBinding
import com.task.noteapp.model.Notes
import com.task.noteapp.model.Tag
import com.task.noteapp.view.MainActivity
import com.task.noteapp.viewmodels.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class EditNoteFragment : Fragment() {
    private var hasNote: Int? = null
    private var oldNote: Notes? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hasNote = it.getInt(ARG_PARAM1)
        }
    }

    private val binding by lazy {
        FragmentEditNoteBinding.inflate(layoutInflater)
    }

    private val viewModel: NotesViewModel by sharedViewModel()
    private var isFieldRequired: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).fabVisible(false)
        binding.run {
            hasNote?.takeIf { it > 0 }?.let { isExist ->
                viewModel.getNoteById(isExist).observe(requireActivity(), { n ->
                        titleEdit.setText(n.title)
                        descriptionEdit.setText(n.description)
                        urlEdit.setText(n.imgUrl)
                        oldNote = n
                    delete.visibility = View.VISIBLE
                    delete.setOnClickListener {
                        deleteNotes()
                        (requireActivity() as MainActivity).launchFragment(ViewNoteFragment.newInstance()).commit()
                    }
                })
            }



            btnEdit.setOnClickListener {
                titleEdit.checkThrowError()
                descriptionEdit.checkThrowError()
                if (isFieldRequired.not()) {
                    if(oldNote!=null)
                      updateNotes()
                    else
                    creatingNewNote()
                    (requireActivity() as MainActivity).launchFragment(ViewNoteFragment.newInstance()).commit()
                }
                isFieldRequired = false
            }
        }

    }

    private fun deleteNotes(){
        viewModel.deleteMark(oldNote!!)

    }

    private fun FragmentEditNoteBinding.updateNotes(){
            val notes = oldNote?.copy(title = titleEdit.text.toString(),description = descriptionEdit.text.toString(),imgUrl = urlEdit.text.toString(),tag = Tag.EDITED)
            viewModel.update(notes!!)
        (requireActivity() as MainActivity).showToast(getString(R.string.note_updated))

    }

    private fun  FragmentEditNoteBinding.creatingNewNote() {
        val note = Notes(name = Date(),
            title = titleEdit.text.toString(),
            description = descriptionEdit.text.toString(),
            imgUrl = urlEdit.text.toString())
        viewModel.createNotes(note)
        (requireActivity() as MainActivity).showToast(getString(R.string.note_created))
    }


    private fun TextInputEditText.checkThrowError() {
        if (this.text.toString().isBlank()) {
            this.error = context.getString(R.string.input_error_msg)
            isFieldRequired = true
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int = 0) =
            EditNoteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }


}