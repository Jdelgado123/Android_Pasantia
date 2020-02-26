package tecsup.example.recyclerview.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tecsup.example.recyclerview.Login;
import tecsup.example.recyclerview.Modelos.Datos_Semanales;
import tecsup.example.recyclerview.Modelos.Loginx;
import tecsup.example.recyclerview.Modelos.Modelo;
import tecsup.example.recyclerview.Modelos.Modelo_raised;
import tecsup.example.recyclerview.Modelos.Shed;
import tecsup.example.recyclerview.Modelos.Shedregister;
import tecsup.example.recyclerview.Modelos.Users;


public interface ApiInterface {

    @GET("api/productionup/")
    Call<List<Modelo>> getDatos(@Header("Authorization") String authorization);

    @GET("api/productiondown/")
    Call<List<Modelo>> getDatos1(@Header("Authorization") String authorization);


    @PUT("api/productionup/{id}/")
    @FormUrlEncoded
    Call<Modelo> updateDatos(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death,
            @Field("package_total") String package_total,
            @Field("leftover_eggs") String leftover_eggs,
            @Field("egg_white") String egg_white,
            @Field("egg_break") String egg_break,
            @Field("egg_dirty") String egg_dirty,
            @Field("observation") String observation);

    @PUT("api/productiondown/{id}/")
    @FormUrlEncoded
    Call<Modelo> updateDatos1(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death,
            @Field("package_total") String package_total,
            @Field("leftover_eggs") String leftover_eggs,
            @Field("egg_white") String egg_white,
            @Field("egg_break") String egg_break,
            @Field("egg_dirty") String egg_dirty,
            @Field("observation") String observation);

    @POST("api/productionup/")
    @FormUrlEncoded
    Call<Modelo> postDatos(
            @Header("Authorization") String authorization,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death,
            @Field("package_total")String package_total,
            @Field("leftover_eggs")String leftover_eggs,
            @Field("egg_white") String egg_white,
            @Field("egg_break") String egg_break,
            @Field("egg_dirty") String egg_dirty,
            @Field("observation") String observation);

    @POST("api/productiondown/")
    @FormUrlEncoded
    Call<Modelo> postDatos1(
            @Header("Authorization") String authorization,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death,
            @Field("package_total")String package_total,
            @Field("leftover_eggs")String leftover_eggs,
            @Field("egg_white") String egg_white,
            @Field("egg_break") String egg_break,
            @Field("egg_dirty") String egg_dirty,
            @Field("observation") String observation);


    /*-- raiseds --*/

    @GET("api/raisedup/")
    Call<List<Modelo_raised>> getRaised(@Header("Authorization") String authorization);

    @GET("api/raiseddown/")
    Call<List<Modelo_raised>> getRaised1(@Header("Authorization") String authorization);

    @GET("api/sheds/")
    Call<List<Shed>> PoblarSpinner(@Header("Authorization") String authorization);

    @PUT("api/raisedup/{id}/")
    @FormUrlEncoded
    Call<Modelo_raised> PutDatosRaised(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death);

    @PUT("api/raiseddown/{id}/")
    @FormUrlEncoded
    Call<Modelo_raised> PutDatosRaised1(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death);

    @POST("api/raisedup/")
    @FormUrlEncoded
    Call<Modelo_raised> PostDatosRaised(
            @Header("Authorization") String authorization,
            @Field("shed_id") String shed,
            @Field("date") String date,
            @Field("food_income") String food_income,
            @Field("food_deposit") String food_deposit,
            @Field("food_consumption") String food_consumption,
            @Field("final_deposit") String final_deposit,
            @Field("chicken_death") String chicken_death,
            @Field("observation") String observation);

    @GET("api/reportpu/")
    Call<List<Datos_Semanales>> GetDatosSemanales(@Header("Authorization") String authorization);

    @GET("api/reportpd/")
    Call<List<Datos_Semanales>> GetDatosSemanales1(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @Headers({
            "Content-Type: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @POST("api/api-token-auth/")
    Call<Loginx> Login(
            @Field(value = "username",encoded = true) String admin,
            @Field(value = "password",encoded = true) String password);

    @GET("api/users/")
    Call<List<Users>> Users();
}

