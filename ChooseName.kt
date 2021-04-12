package com.example.tictactoe_191rdb036

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChooseName : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_name)

        val submit: Button = findViewById(R.id.submit)
        submit.setOnClickListener {
            val intent = Intent(applicationContext, ActivityF::class.java)
            //lai nodotu speletaju vardus uz citu Activity
            val editTextP1 : EditText = findViewById(R.id.editTextPlayer1)
            val editTextP2 : EditText = findViewById(R.id.editTextPlayer2)
            val namePlayer1 = editTextP1.text.toString()
            val namePlayer2 = editTextP2.text.toString()
            intent.putExtra("string1", namePlayer1)
            intent.putExtra("string2", namePlayer2)
            startActivity(intent)
        }

        val goBackToMenu: Button = findViewById(R.id.goBackToMenu)
        goBackToMenu.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}