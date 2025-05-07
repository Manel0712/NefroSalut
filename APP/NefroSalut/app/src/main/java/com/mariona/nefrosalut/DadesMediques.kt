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
        var DNI = binding.inputNomDieta.text.toString()
        var data = binding.inputData.text.toString()
        var pes = binding.inputPes.text.toString().toDouble()
        var alçada = binding.inputAlcada.text.toString().toDouble()
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
        if (IMC>=18.5 && IMC<=24.9) {
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
        var paciente = Paciente(name, apellidos, email, telefono, password.trim(), estat, "animo", activitatFisica, diabetic, hipertensio, estadio, 0, null, DNI, data, pes, alçada, IMC, classificacio, 0)
        viewModel.register(paciente)
    }
}