package com.task.noteapp.view

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.task.noteapp.databinding.ActivityMainBinding
import com.task.noteapp.view.adapter.EditNoteFragment
import com.task.noteapp.view.adapter.ViewNoteFragment
import com.zuper.todo.BaseActivity

class MainActivity : BaseActivity() {


    override fun getContentLayout(): View = binding.root

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate() {
        initView()
    }

    private fun initView() {
        binding.fab.setOnClickListener {
            launchEditFragment(EditNoteFragment.newInstance()).commit()
        }
        launchFragment(ViewNoteFragment.newInstance()).commit()
    }



     fun launchFragment(frag:Fragment):FragmentTransaction  =
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id,
                frag)

    fun launchEditFragment(frag:Fragment):FragmentTransaction  =
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(binding.container.id,
                frag)

    fun fabVisible(flag:Boolean){
        binding.fab.isVisible = flag
    }

    override fun onBackPressed() {
        if(supportFragmentManager.fragments[1] is EditNoteFragment){
            fabVisible(true)
           super.onBackPressed()
        }else finish()
    }


}