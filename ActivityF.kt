package com.example.tictactoe_191rdb036

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityF : AppCompatActivity() {

    lateinit var buttons: Array<Array<Button>>
    private var player1Move: Boolean = true
    private var moveCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f)

        //atgriezties
        val gobackBtn: Button = findViewById(R.id.gobackBtn)
        gobackBtn.setOnClickListener{
            val intent = Intent(this, ChooseName::class.java)
            startActivity(intent)
        }

        //notirit speles laukumu
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
                //katrai pogai savs id (btn00, btn01, ...) un nemam divus pedejus ciparus
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
        } else {
            btn.text = "O"
        }
        moveCount++
        //gajiena maina
        player1Move = !player1Move

        //tiek izsauktas uzvaras un neizskirta funkcijas
        if(win1()){
            val nameP1 : String? = intent.getStringExtra("string1")
            //nemam vardu no ieprieksejas Activity
            Toast.makeText(applicationContext, nameP1 + " won!", Toast.LENGTH_SHORT).show()
            deleteXO()
        }else if(win2()){
            //nemam vardu no ieprieksejas Activity
            val nameP2 : String? = intent.getStringExtra("string2")
            Toast.makeText(applicationContext, nameP2 + " won!", Toast.LENGTH_SHORT).show()
            deleteXO()
        }else{
            if (moveCount == 9)
                draw()
        }
    }

    private fun win1(): Boolean {
        val squares = Array(3){r->
            Array(3){c->
                buttons[r][c].text
            }
        }
        //defineti visi (8) uzvaras stavokli
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
        //defineti visi (8) uzvaras stavokli
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

    //notirit speles laukumu
    private fun deleteXO(){
        for (r in 0..2){
            for(c in 0..2){
                buttons[r][c].text = ""
            }
        }
        //kad laukums notirits, atkal gajiens X-am un gajienu skaits ir 0
        moveCount = 0
        player1Move = true
    }
}