package com.task.noteapp.util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.task.noteapp.model.Notes

class ItemDiffCallback(private val oldList: List<Notes>, private val newList: List<Notes>): DiffUtil.Callback() {



    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {


        return oldList[oldPosition]== newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}