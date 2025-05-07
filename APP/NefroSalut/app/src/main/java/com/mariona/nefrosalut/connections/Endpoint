package com.mariona.nefrosalut.connections

import com.mariona.nefrosalut.models.Aliments
import com.mariona.nefrosalut.models.Categoria
import com.mariona.nefrosalut.models.Dietas
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente
import com.mariona.nefrosalut.models.Videos
import retrofit2.Response
import retrofit2.http.*
import okhttp3.ResponseBody

interface Endpoint {

    @POST("api/paciente/loggin")
    suspend fun loggin(@Query("email") email: String, @Query("password") password: String): Response<List<Paciente>>

    @POST("api/paciente")
    suspend fun register(@Query("nombre") nombre: String, @Query("apellidos") apellidos: String, @Query("email") email: String, @Query("telefono") telefono: String, @Query("contraseña") contraseña: String, @Query("estado_enfermedad") estadoEnfermedad: String, @Query("estado_animo") estadoAnimo: String, @Query("actividad_fisica") actividadFisica: Boolean, @Query("diabetico") diabetico: Boolean, @Query("hipertenso") hipertenso: Boolean, @Query("estadio") estadio: String, @Query("puntos") puntos: Int, @Query("DNI") DNI: String, @Query("fecha_nacimiento") fechaNacimiento: String, @Query("peso") peso: Double, @Query("altura") altura: Double, @Query("IMC") IMC: Double, @Query("clasificacion") clasificacion: String, @Query("progreso_id") progresoId: Int): Response<ResponseBody>

    @POST("api/familiar/loggin")
    suspend fun logginFamiliar(@Query("email") email: String, @Query("password") password: String): Response<List<Familiar>>

    @POST("api/familiar")
    suspend fun registerFamiliar(@Query("nombre") nombre: String, @Query("apellidos") apellidos: String, @Query("email") email: String, @Query("telefono") telefono: String, @Query("contraseña") contraseña: String): Response<ResponseBody>

    @GET("api/dieta")
    suspend fun dietas(): Response<List<Dietas>>

    @GET("api/dieta/{dieta}/platos")
    suspend fun dietaPlatos(@Path("dieta") id: Int): Response<List<Aliments>>

    @GET("api/dieta/{dieta}/platos/{categoria}")
    suspend fun dietaPlatosCategoria(@Path("dieta") id: Int, @Path("categoria") categoria: String): Response<List<Aliments>>

    @GET("api/video")
    suspend fun videos(): Response<List<Videos>>

    @GET("api/video/categoria/{categoria}")
    suspend fun videosCategoria(@Path("categoria") categoria: String): Response<List<Videos>>

    @GET("api/categoria/{id}")
    suspend fun categoriaNombre(@Path("id") id: String): Response<Categoria>

}
