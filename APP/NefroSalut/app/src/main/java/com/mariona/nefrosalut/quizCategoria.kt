package com.mariona.nefrosalut

import androidx.appcompat.app.AppCompatActivity

class quizCategoria   : AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_categoria)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
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
        i.putExtra("categoria", "predialisis")
        startActivity(i)
    }

    fun categoriaHemodialisis(view: android.view.View) {
        val i = android.content.Intent(this, Quiz::class.java)
        i.putExtra("categoria", "hemodialisis")
        startActivity(i)
    }

    fun categoriaPerioneal(view: android.view.View) {
        val i = android.content.Intent(this, Quiz::class.java)
        i.putExtra("categoria", "peritoneal")
        startActivity(i)
    }
}