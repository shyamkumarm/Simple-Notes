package com.task.noteapp.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentViewNoteBinding
import com.task.noteapp.model.Tag
import com.task.noteapp.view.MainActivity
import com.task.noteapp.viewmodels.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class ViewNoteFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val binding by lazy {
        FragmentViewNoteBinding.inflate(layoutInflater)
    }

     private val viewModel: NotesViewModel by  sharedViewModel()
    private lateinit var viewAdapter: ViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).fabVisible(true)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            viewAdapter =  ViewAdapter { _, data ->
                if(data.tag != Tag.DELETED) {
                    (requireActivity() as MainActivity).launchEditFragment(EditNoteFragment.newInstance(
                        data.id)).commit()
                }else (requireActivity() as MainActivity).showToast(context.getString(R.string.already_deleted))
            }
            adapter = viewAdapter

        }

        viewModel.getAllNotes().observe(requireActivity(),{

            binding.recyclerView.post {
                viewAdapter.update(it)
            }

        })

    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ViewNoteFragment().apply {

            }
    }
}