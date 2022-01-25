package com.example.memorygame

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.databinding.ItemCardBinding
import com.example.memorygame.model.BoardSize
import kotlin.math.min

class CardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cardImages: List<Int>) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private lateinit var binding: ItemCardBinding

    companion object {
        private const val MARGIN_SIZE = 15
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCardBinding.inflate(LayoutInflater.from(context), parent, false)
        val cardWidth = parent.width / boardSize.getWidth() - (2 * MARGIN_SIZE)
        val cardHeight = parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
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

    override fun getItemCount(): Int = boardSize.numCards

    inner class ViewHolder(binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.ibCard.setImageResource(cardImages[position])
            binding.ibCard.setOnClickListener {
                println("Clicked on position $position")
            }
        }
    }
}
