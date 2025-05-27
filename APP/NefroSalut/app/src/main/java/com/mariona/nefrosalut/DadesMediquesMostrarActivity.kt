package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mariona.nefrosalut.databinding.DadesMediquesMostrarBinding
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente

private lateinit var user: Any
private lateinit var rol: String
class DadesMediquesMostrarActivity : AppCompatActivity() {
    private lateinit var binding: DadesMediquesMostrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DadesMediquesMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rol = intent.extras!!.getString("rol").toString()
        if (rol.equals("Paciente")) {
            user = intent.extras!!.getSerializable("user") as Paciente
        }
        else if (rol.equals("Familiar")) {
            user = intent.extras!!.getSerializable("user") as Familiar
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val dni = intent.getStringExtra("dni") ?: "No disponible"
        val dataNaixement = intent.getStringExtra("dataNaixement") ?: "No disponible"
        val pes = intent.getDoubleExtra("pes", 0.0)
        val alcada = intent.getDoubleExtra("alcada", 0.0)
        val activitatFisica = intent.getBooleanExtra("activitatFisica", false)
        val hipertensio = intent.getBooleanExtra("hipertensio", false)
        val diabetic = intent.getBooleanExtra("diabetic", false)
        val estat = intent.getStringExtra("estat") ?: "No disponible"
        val fase = intent.getStringExtra("fase") ?: "No disponible"
        val nom = intent.getStringExtra("nom") ?: "No disponible"
        val cognoms = intent.getStringExtra("cognoms") ?: ""

        binding.tvNomCognomsDades.text = "$nom, $cognoms"

        binding.tvDniDade.text = dni
        binding.tvDataNaixementDades.text = dataNaixement
        binding.tvPesDades.text = if (pes != 0.0) pes.toString() else "No disponible"
        binding.tvAlcadaDades.text = if (alcada != 0.0) alcada.toString() else "No disponible"
        binding.tvActividadFisicaDades.text = if (activitatFisica) "Sí" else "No"
        binding.tvHipertensioDades.text = if (hipertensio) "Sí" else "No"
        binding.tvDiabeticoDades.text = if (diabetic) "Sí" else "No"
        binding.tvEstatDades.text = estat
        binding.tvFaseDades.text = fase
    }

    fun volverOnClick(view: View){
        val intent = Intent(this, Perfil::class.java)
        if (rol.equals("Paciente")) {
            intent.putExtra("user", user as Paciente)
        } else if (rol.equals("Familiar")) {
            intent.putExtra("user", user as Familiar)
        }
        intent.putExtra("rol", rol)
        startActivity(intent)
    }
}
