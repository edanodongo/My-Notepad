package com.notes.mynotepad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.notes.mynotepadbeta10.R


class NoteRVAdapter(
    val context: Context,
    private val noteClickInterface: NoteClickListener,
    private val noteClickDeleteInterface: NoteClickListener,
    private val noteLongClickInterface: NoteClickListener,
    ) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>(), Filterable {

    private var allNotes = ArrayList<Note>()

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNoteTitle)!!
        val descTV = itemView.findViewById<TextView>(R.id.idNoteDesc)!!
        val cardRelative = itemView.findViewById<RelativeLayout>(R.id.cardRelative)!!
        val timeTV = itemView.findViewById<TextView>(R.id.idTVTimeStamp)!!
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)!!
        val pin = itemView.findViewById<ImageView>(R.id.pin)!!
        val cardV = itemView.findViewById<CardView>(R.id.cardV)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        /*val layoutParams = StaggeredGridLayoutManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        layoutParams.height = 200
        itemView.setLayoutParams(layoutParams)*/
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.text = allNotes[position].noteTitle
        holder.descTV.text = allNotes[position].noteDescription
        holder.timeTV.text = "Last updated : " + allNotes[position].timeStamp

        holder.pin.setOnClickListener {
            if(allNotes[position].notePinned){
                holder.pin.visibility = View.VISIBLE
            }else{
                holder.pin.visibility = View.GONE
            }
        }

        if(allNotes[position].notePinned){
            holder.pin.visibility = View.VISIBLE
        }else{
            holder.pin.visibility = View.GONE
        }

        /*val layoutParams = holder.itemView
            .layoutParams as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = position % 15 === 0

        layoutParams.height = (200 + Math.random() * 1000).toInt()
        holder.itemView.layoutParams = layoutParams*/

        if((allNotes[position].relativeBG) == 1){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_blueg)
        }else if((allNotes[position].relativeBG) == 2){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_blue)
        }else if((allNotes[position].relativeBG) == 3){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_yell)
        }else if((allNotes[position].relativeBG) == 4){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_purp)
        }else if((allNotes[position].relativeBG) == 5){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_green)
        }else if((allNotes[position].relativeBG) == 6){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_teal)
        }else if((allNotes[position].relativeBG) == 7){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_orange)
        }else if((allNotes[position].relativeBG) == 8){
            holder.cardRelative.setBackgroundResource(R.drawable.relative_white)
            holder.deleteIV.setImageResource(R.drawable.ic_delete_black)
        }else{
            holder.cardRelative.setBackgroundResource(R.drawable.relative_blueg)
        }

        holder.deleteIV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(allNotes[position])
        }

        holder.itemView.setOnLongClickListener{
            noteLongClickInterface.onNoteLongClicked(allNotes[position], holder.cardV)
            //noteClickInterface.onNoteLongClicked(allNotes[position], holder.cardV)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}

