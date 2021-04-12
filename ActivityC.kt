package com.example.tictactoe_191rdb036

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class ActivityC : AppCompatActivity() {

    lateinit var buttons: Array<Array<Button>>
    private var player1Move: Boolean = true
    private var moveCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        val gobackBtn: Button = findViewById(R.id.gobackBtn)
        gobackBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val resetBtn: Button = findViewById(R.id.resetBtn)
        resetBtn.setOnClickListener{
            deleteXO()
        }
        buttons = Array(3) { r ->
            Array(3) { c ->
                initButton(r, c)
            }
        }
    }

    private fun initButton(r: Int, c: Int): Button {
        val btn: Button =
                findViewById(resources.getIdentifier("btn$r$c", "id", packageName))
        btn.setOnClickListener {
            onBtnClick(btn)
        }
        return btn
    }

        private fun onBtnClick(btn: Button) {
        if (btn.text != "") return
        if (player1Move) {
            btn.text = "X"
            player1Move = !player1Move
        } else {
            btn.text = "O"
            player1Move = true
        }
        moveCount++

        if(win1()){
            Toast.makeText(applicationContext, "You won!", Toast.LENGTH_SHORT).show()
            player1Move = true
            deleteXO()
        }else if(win2()){
            Toast.makeText(applicationContext, "Computer won!", Toast.LENGTH_SHORT).show()
            player1Move = true
            deleteXO()
        }else{
            if (moveCount == 9) {
                player1Move = true
                draw()
            }
        }
            //tiek izsaukta funkcija, kura atbildiga par random "O" novietosanu
            computer()
    }

    private fun computer(){
        val squares = Array(3){r->
            Array(3){c->
                buttons[r][c]
            }
        }
        //random skaitli prieks rindam un kolonnam
        var randomSquareR = Random.nextInt(0, 3)
        var randomSquareC = Random.nextInt(0, 3)

        //random kamer neatradis tuksu pogu
        while(true){
            randomSquareR = Random.nextInt(0, 3)
            randomSquareC = Random.nextInt(0, 3)
            if (squares[randomSquareR][randomSquareC].text == "")
                break
        }
        if (!player1Move)
            onBtnClick(squares[randomSquareR][randomSquareC])
    }

    private fun win1(): Boolean {
        val squares = Array(3){r->
            Array(3){c->
                buttons[r][c].text
            }
        }

        if (squares[0][0] == "X" && squares[0][1] == "X" && squares[0][2] == "X")
            return true
        if ((squares[1][0] == "X" && squares[1][1] == "X" && squares[1][2] == "X"))
            return true
        if ((squares[2][0] == "X" && squares[2][1] == "X" && squares[2][2] == "X"))
            return true
        if ((squares[0][0] == "X" && squares[1][0] == "X" && squares[2][0] == "X"))
            return true
        if ((squares[0][1] == "X" && squares[1][1] == "X" && squares[2][1] == "X"))
            return true
        if ((squares[0][2] == "X" && squares[1][2] == "X" && squares[2][2] == "X"))
            return true
        if ((squares[0][0] == "X" && squares[1][1] == "X" && squares[2][2] == "X"))
            return true
        if ((squares[0][2] == "X" && squares[1][1] == "X" && squares[2][0] == "X"))
            return true
        return false
    }

    private fun win2(): Boolean {
        val squares = Array(3){r->
            Array(3){c->
                buttons[r][c].text
            }
        }
        if (squares[0][0] == "O" && squares[0][1] == "O" && squares[0][2] == "O")
            return true
        if ((squares[1][0] == "O" && squares[1][1] == "O" && squares[1][2] == "O"))
            return true
        if ((squares[2][0] == "O" && squares[2][1] == "O" && squares[2][2] == "O"))
            return true
        if ((squares[0][0] == "O" && squares[1][0] == "O" && squares[2][0] == "O"))
            return true
        if ((squares[0][1] == "O" && squares[1][1] == "O" && squares[2][1] == "O"))
            return true
        if ((squares[0][2] == "O" && squares[1][2] == "O" && squares[2][2] == "O"))
            return true
        if ((squares[0][0] == "O" && squares[1][1] == "O" && squares[2][2] == "O"))
            return true
        if ((squares[0][2] == "O" && squares[1][1] == "O" && squares[2][0] == "O"))
            return true
        return false
    }

    private fun draw() {
        Toast.makeText(applicationContext, "It's a draw!", Toast.LENGTH_SHORT).show()
        deleteXO()
    }

    private fun deleteXO(){
        for (r in 0..2){
            for(c in 0..2){
                buttons[r][c].text = ""
            }
        }
        moveCount = 0
        player1Move = true
    }
}