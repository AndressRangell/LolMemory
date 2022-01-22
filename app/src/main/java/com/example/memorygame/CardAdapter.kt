package com.example.memorygame

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.databinding.ItemCardBinding
import kotlin.math.min

class CardAdapter(private val context: Context, private val numberPieces: Int) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private lateinit var binding: ItemCardBinding

    companion object {
        private const val MARGIN_SIZE = 15
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCardBinding.inflate(LayoutInflater.from(context), parent, false)
        val cardWidth = parent.width / 3 - (2 * MARGIN_SIZE)
        val cardHeight = parent.height / 4 - (2 * MARGIN_SIZE)
        val cardSideLength = min(cardWidth, cardHeight)
        val layout = binding.cvItem.layoutParams as ViewGroup.MarginLayoutParams
        layout.width = cardSideLength
        layout.height = cardSideLength
        layout.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = numberPieces

    inner class ViewHolder(binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.ibCard.setOnClickListener {
                println("Clicked on position $position")
            }
        }
    }
}
