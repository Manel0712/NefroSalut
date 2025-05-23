package com.mariona.nefrosalut

import androidx.appcompat.app.AppCompatActivity
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente

class quizCategoria   : AppCompatActivity() {

    private lateinit var user: Any
    private lateinit var rol: String

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_categoria)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        rol = intent.extras!!.getString("rol").toString()
        if (rol.equals("Paciente")) {
            user = intent.extras!!.getSerializable("user") as Paciente
        }
        else if (rol.equals("Familiar")) {
            user = intent.extras!!.getSerializable("user") as Familiar
        }
    }
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnvideos -> startActivity(android.content.Intent(this, verVideos::class.java))
            R.id.btnjocs -> startActivity(android.content.Intent(this, Quiz::class.java))
            R.id.btnplats -> startActivity(android.content.Intent(this, dietasPlats::class.java))
            R.id.btnperfil -> startActivity(android.content.Intent(this, Perfil::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    fun categoriaPredialisis(view: android.view.View) {
        val i = android.content.Intent(this, Quiz::class.java)
        i.putExtra("categoria", "Prediàlisi")
        if (rol.equals("Paciente")) {
            i.putExtra("user", user as Paciente)
        } else if (rol.equals("Familiar")) {
            i.putExtra("user", user as Familiar)
        }
        i.putExtra("rol", rol)

        startActivity(i)
    }

    fun categoriaHemodialisis(view: android.view.View) {
        val i = android.content.Intent(this, Quiz::class.java)
        i.putExtra("categoria", "Hemodiálisis")
        if (rol.equals("Paciente")) {
            i.putExtra("user", user as Paciente)
        } else if (rol.equals("Familiar")) {
            i.putExtra("user", user as Familiar)
        }
        i.putExtra("rol", rol)

        startActivity(i)
    }

    fun categoriaPerioneal(view: android.view.View) {
        val i = android.content.Intent(this, Quiz::class.java)
        i.putExtra("categoria", "Peritoneal")
        if (rol.equals("Paciente")) {
            i.putExtra("user", user as Paciente)
        } else if (rol.equals("Familiar")) {
            i.putExtra("user", user as Familiar)
        }
        i.putExtra("rol", rol)

        startActivity(i)
    }
}