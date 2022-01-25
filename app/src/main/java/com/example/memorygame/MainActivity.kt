package com.example.memorygame

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.model.BoardSize
import com.example.memorygame.model.Card
import com.example.memorygame.model.MemoryGame
import com.example.memorygame.util.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: CardAdapter
    private var boardSize: BoardSize = BoardSize.HARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memoryGame = MemoryGame(boardSize)

        adapter = CardAdapter(this, boardSize, memoryGame.cards,
            object: CardAdapter.CardClickListener {
                override fun onCardClicked(position: Int) {
                    updateGameWithFlip(position)
                }
            })
        binding.rvCards.adapter = adapter
        binding.rvCards.setHasFixedSize(true)
        binding.rvCards.layoutManager = GridLayoutManager(this, boardSize.getWidth())

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateGameWithFlip(position: Int){
        if(memoryGame.haveWonGame()){
            Toast.makeText(this, "Juego finalizado", Toast.LENGTH_SHORT).show()
            return
        }
        if(memoryGame.isCardFaceUp(position)){
            Toast.makeText(this, "Movimiento no valido", Toast.LENGTH_SHORT).show()
            return
        }
        if(memoryGame.flipCard(position)){
            Toast.makeText(this, "Par encontrado", Toast.LENGTH_SHORT).show()
        }
        adapter.notifyDataSetChanged()
    }
}