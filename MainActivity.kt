package com.example.tictactoe_191rdb036

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonF: Button = findViewById(R.id.buttonF)
        buttonF.setOnClickListener{
            val intent = Intent(this, ChooseName::class.java)
            startActivity(intent)
        }

        val buttonC: Button = findViewById(R.id.buttonC)
        buttonC.setOnClickListener{
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }

        val how: Button = findViewById(R.id.how)
        how.setOnClickListener{
            val intent = Intent(this, howToPlay::class.java)
            startActivity(intent)
        }
        Toast.makeText(
            applicationContext,
            "Welcome, dear friend!",
            Toast.LENGTH_SHORT).show()
    }
}