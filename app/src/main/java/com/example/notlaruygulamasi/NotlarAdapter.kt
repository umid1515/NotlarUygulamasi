package com.example.notlaruygulamasi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notlaruygulamasi.databinding.CardTasarimBinding
import java.util.ArrayList

class NotlarAdapter : RecyclerView.Adapter<NotlarAdapter.NotlarHolder>() {


    private val notlarList= arrayListOf<Notlar>()
    class NotlarHolder(
        private val cardTasarimBinding: CardTasarimBinding
    ) : RecyclerView.ViewHolder(cardTasarimBinding.root) {
        fun find(not: Notlar) {
            with(cardTasarimBinding) {
                textViewNot1.text = not.not1
                textViewNot2.text = not.not2
                textViewDers.text= not.dersAdi
                notCard.setOnClickListener {

                    val intent = Intent(cardTasarimBinding.root.context,DetayActivity::class.java)
                    intent.putExtra("nesne", not)
                    cardTasarimBinding.root.context.startActivity(intent)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotlarHolder {
       val layout =CardTasarimBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotlarHolder(layout)
    }

    override fun onBindViewHolder(holder: NotlarHolder, position: Int) {

        val not =notlarList[position]
        holder.find(not)

    }

    override fun getItemCount(): Int {
       return notlarList.size
    }

    fun updateList(list:List<Notlar>){
        notlarList.clear()
        notlarList.addAll(list)
        notifyDataSetChanged()
    }


}