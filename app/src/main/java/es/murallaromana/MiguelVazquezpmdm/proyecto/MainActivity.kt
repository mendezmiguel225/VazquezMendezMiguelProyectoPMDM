package es.murallaromana.MiguelVazquezpmdm.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var bttSign:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bttSign =findViewById(R.id.bttSign)
        bttSign.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)

        }
    }
}