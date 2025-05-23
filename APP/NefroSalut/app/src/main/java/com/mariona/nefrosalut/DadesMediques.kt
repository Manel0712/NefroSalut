package com.mariona.nefrosalut

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.databinding.DadesMediquesBinding
import com.mariona.nefrosalut.models.Paciente
import com.mariona.nefrosalut.models.Progreso
import com.mariona.nefrosalut.viewModels.RegisterPacienteViewModel
import com.mariona.nefrosalut.viewModels.RegisterPacienteViewModelFactory

class DadesMediques : AppCompatActivity() {
    private val viewModel: RegisterPacienteViewModel by viewModels { RegisterPacienteViewModelFactory() }
    private lateinit var binding: DadesMediquesBinding
    private lateinit var name: String
    private lateinit var apellidos: String
    private lateinit var email: String
    private lateinit var telefono: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DadesMediquesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.inputNomDieta.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val dni = binding.inputNomDieta.text.toString()
                /*if (!validarDNI(dni)) {
                    binding.inputNomDieta.error = "DNI no válido"
                } else {
                    binding.inputNomDieta.error = null
                }*/
            }
        }
        name = intent.extras!!.getString("name").toString()
        apellidos = intent.extras!!.getString("apellidos").toString()
        email = intent.extras!!.getString("email").toString()
        telefono = intent.extras!!.getString("telefono").toString()
        password = intent.extras!!.getString("password").toString()

        viewModel.error.observe(this) {
            if (it.equals("Paciente registrado correctamente")) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            else if (it != null) {
                val snackbar = Snackbar.make(view, it,
                    Snackbar.LENGTH_LONG).setAction("Action", null)
                snackbar.setActionTextColor(Color.WHITE)
                val snackbarView = snackbar.view
                snackbarView.setBackgroundColor(Color.RED)
                val textView =
                    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                textView.textSize = 28f
                snackbar.show()
            }
        }
    }

    fun añadirDatosMedicos(view: View) {
        val DNI = binding.inputNomDieta.text.toString()
        /*if (!validarDNI(DNI)) {
            binding.inputNomDieta.error = "DNI no válido"
            return
        }*/

        val pesString = binding.inputPes.text.toString().replace(",", ".")
        val alçadaString = binding.inputAlcada.text.toString().replace(",", ".")

        val pes = pesString.toDoubleOrNull()
        val alçada = alçadaString.toDoubleOrNull()

        if (pes == null) {
            binding.inputPes.error = "Peso inválido"
            return
        } else {
            binding.inputPes.error = null
        }

        if (alçada == null) {
            binding.inputAlcada.error = "Altura inválida"
            return
        } else {
            binding.inputAlcada.error = null
        }

        var data = binding.inputData.text.toString()


        if (pes == null || alçada == null) {
            Snackbar.make(view, "Peso o altura inválidos", Snackbar.LENGTH_LONG).apply {
                setActionTextColor(Color.WHITE)
                view.setBackgroundColor(Color.RED)
                val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                textView.setTextColor(Color.WHITE)
                textView.textSize = 24f
            }.show()
            return
        }

        var activitatFisica = false
        if (binding.checkboxActividadFisicaSi.isChecked) {
            activitatFisica = true
        }
        else if (binding.checkboxActividadFisicaNo.isChecked) {
            activitatFisica = false
        }
        var hipertensio = false
        if (binding.checkboxHipertenso.isChecked) {
            hipertensio = true
        }
        else {
            hipertensio = false
        }
        var diabetic = false
        if (binding.checkboxDiabetico.isChecked) {
            diabetic = true
        }
        else {
            diabetic = false
        }
        var estat = binding.inputEstat.selectedItem.toString()
        var estadio = binding.inputClassificacio.selectedItem.toString()
        var IMC = pes / (alçada*alçada)
        var classificacio = ""
        if (IMC>=18.5 && IMC<=25.0) {
            if (activitatFisica) {
                if (estadio.equals("Tractament conservador")) {
                    if (diabetic) {
                        classificacio = "C2"
                    }
                    else {
                        classificacio = "C1"
                    }
                }
                else {
                    if (diabetic) {
                        classificacio = "C4"
                    }
                    else {
                        classificacio = "C3"
                    }
                }
            }
            else {
                if (estadio.equals("Tractament conservador")) {
                    if (diabetic) {
                        classificacio = "C6"
                    }
                    else {
                        classificacio = "C5"
                    }
                }
                else {
                    if (diabetic) {
                        classificacio = "C8"
                    }
                    else {
                        classificacio = "C7"
                    }
                }
            }
        }
        else if (IMC<18.5) {
            if (activitatFisica) {
                if (estadio.equals("Tractament conservador")) {
                    if (diabetic) {
                        classificacio = "C10"
                    } else {
                        classificacio = "C9"
                    }
                } else {
                    if (diabetic) {
                        classificacio = "C12"
                    } else {
                        classificacio = "C11"
                    }
                }
            } else {
                if (estadio.equals("Tractament conservador")) {
                    if (diabetic) {
                        classificacio = "C14"
                    } else {
                        classificacio = "C13"
                    }
                } else {
                    if (diabetic) {
                        classificacio = "C16"
                    } else {
                        classificacio = "C15"
                    }
                }
            }
        }
        else if (IMC>25.0) {
            if (activitatFisica) {
                if (estadio.equals("Tractament conservador")) {
                    if (diabetic) {
                        classificacio = "C18"
                    } else {
                        classificacio = "C17"
                    }
                } else {
                    if (diabetic) {
                        classificacio = "C20"
                    } else {
                        classificacio = "C19"
                    }
                }
            } else {
                if (estadio.equals("Tractament conservador")) {
                    if (diabetic) {
                        classificacio = "C22"
                    } else {
                        classificacio = "C21"
                    }
                } else {
                    if (diabetic) {
                        classificacio = "C24"
                    } else {
                        classificacio = "C23"
                    }
                }
            }
        }
        val progresoInicial = Progreso(
            powerUps = mapOf(
                "videos_vistos" to 0,
                "quizzes_fets" to 0,
                "power1" to 0,
                "power2" to 0
            ),
            monedas = 0,
            puntos = 0
        )

        val paciente = Paciente(
            0, name, apellidos, email, telefono, password.trim(), estat, "animo",
            activitatFisica, diabetic, hipertensio, estadio, 0, null, DNI, data,
            pes, alçada, IMC, classificacio, 0
        )


        viewModel.register(paciente)
    }
    /*fun validarDNI(dni: String): Boolean {
        val dniRegex = Regex("^[0-9]{8}[A-HJ-NP-TV-Z]$")
        if (!dniRegex.matches(dni.uppercase())) return false

        val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
        val numero = dni.substring(0, 8).toIntOrNull() ?: return false
        val letraEsperada = letras[numero % 23]
        val letraReal = dni[8].uppercaseChar()
        return letraEsperada == letraReal
    }*/

}