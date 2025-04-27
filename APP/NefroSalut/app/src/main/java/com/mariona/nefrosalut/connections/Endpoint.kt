package com.mariona.nefrosalut.connections

import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.LoginPaciente
import com.mariona.nefrosalut.models.Paciente
import retrofit2.Response
import retrofit2.http.*
import okhttp3.ResponseBody

interface Endpoint {

    @POST("api/paciente/loggin")
    suspend fun loggin(@Query("email") email: String, @Query("password") password: String): Response<List<Paciente>>

    @POST("api/paciente")
    suspend fun register(@Query("nombre") nombre: String, @Query("apellidos") apellidos: String, @Query("email") email: String, @Query("telefono") telefono: String, @Query("contraseña") contraseña: String, @Query("estado_enfermedad") estadoEnfermedad: String, @Query("estado_animo") estadoAnimo: String, @Query("actividad_fisica") actividadFisica: Boolean, @Query("diabetico") diabetico: Boolean, @Query("hipertenso") hipertenso: Boolean, @Query("puntos") puntos: Int, @Query("DNI") DNI: String, @Query("fecha_nacimiento") fechaNacimiento: String, @Query("peso") peso: Double, @Query("altura") altura: Double, @Query("IMC") IMC: Double, @Query("clasificacion") clasificacion: String, @Query("progreso_id") progresoId: Int): Response<ResponseBody>

    @POST("api/familiar/loggin")
    suspend fun logginFamiliar(@Query("email") email: String, @Query("password") password: String): Response<List<Familiar>>

    @POST("api/familiar")
    suspend fun registerFamiliar(@Query("nombre") nombre: String, @Query("apellidos") apellidos: String, @Query("email") email: String, @Query("telefono") telefono: String, @Query("contraseña") contraseña: String): Response<ResponseBody>

}