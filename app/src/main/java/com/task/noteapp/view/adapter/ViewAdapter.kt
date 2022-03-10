package com.task.noteapp.view.adapter

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.databinding.ItemLayoutBinding
import com.task.noteapp.model.Notes
import com.task.noteapp.model.Tag
import com.task.noteapp.util.ItemDiffCallback
import com.task.noteapp.view.adapter.ViewAdapter.ViewHolder
import kotlin.random.Random


class ViewAdapter(
    private val notes: MutableList<Notes> = mutableListOf(), val callback: (Int, Notes) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {

    private val TAG = "ViewAdapter"


    private var getLastClickedPos: Int = 0

    val colors = listOf(Color.BLUE, Color.MAGENTA,Color.RED,Color.DKGRAY)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notes[position]

        Log.d(TAG, item.toString())
        holder.mView.root.setOnClickListener {
            getLastClickedPos = holder.adapterPosition
            callback(
                getLastClickedPos,
                notes[getLastClickedPos]
            )
        }

        holder.mView.apply {
            name.text = item.getFormat()
            title.text = item.title
            txtDescription.text = item.description
            val format = if (item.tag == Tag.DELETED) Paint.STRIKE_THRU_TEXT_FLAG else Paint.ANTI_ALIAS_FLAG
            title.paintFlags = format
            txtDescription.paintFlags = format
            tagView.text =  when(item.tag){
                Tag.DELETED -> tagView.context.getString(R.string.delete)
                Tag.NEW -> tagView.context.getString(R.string.new_str)
              else -> tagView.context.getString(R.string.edited)
            }
             linear.setBackgroundColor(colors[Random.nextInt(0,colors.size)])

        }

    }

    fun update(notes: ArrayList<Notes>) {
        val diffCallback = ItemDiffCallback(this.notes, notes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.notes.clear()
        this.notes.addAll(notes)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class ViewHolder(val mView: ItemLayoutBinding) : RecyclerView.ViewHolder(mView.root)

    override fun getItemCount() = notes.size

}



