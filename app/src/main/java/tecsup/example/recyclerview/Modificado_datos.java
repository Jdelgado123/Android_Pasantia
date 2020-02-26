package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tecsup.example.recyclerview.Interface.ApiInterface;
import tecsup.example.recyclerview.Modelos.Modelo;

public class Modificado_datos extends AppCompatActivity {

    private EditText mS_anterior,mE_ingreso,mConsumo,mE_final,mE_chickendeath,mE_date,mE_package,mE_leftovereggs,mE_observation,mE_egg_white,mE_egg_break,mE_egg_dirty;

    String id;
    String shed,food_income,food_deposit,food_consumption,final_deposit,chicken_death,date,packagee,leftovereggs,huevos_blancos,huevos_sucios,huevos_rotos,observation;

    private String recibido,granja,token_reci,group;
    private String verificacion_Arriva="Granja_Arriva";

    androidx.appcompat.widget.Toolbar toolbar;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificado_datos);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        mS_anterior=findViewById(R.id.E_s_anterior);
        mE_date=findViewById(R.id.E_date);
        mE_ingreso=findViewById(R.id.E_ingreso);
        mConsumo=findViewById(R.id.E_consumo);
        mE_final=findViewById(R.id.E_s_final);
        mE_chickendeath=findViewById(R.id.E_chicken_death);
        mE_package=findViewById(R.id.E_package);
        mE_leftovereggs=findViewById(R.id.E_leftovereggs);
        mE_observation=findViewById(R.id.E_observation);
        mE_egg_white=findViewById(R.id.E_huevos_blancos);
        mE_egg_break=findViewById(R.id.E_huevos_rotos);
        mE_egg_dirty=findViewById(R.id.E_huevos_sucios);
        toolbar=findViewById(R.id.mi_toolbar);


        Intent intent=this.getIntent();
        token_reci=intent.getStringExtra("tokencito");
        id=intent.getStringExtra("id");
        date=intent.getStringExtra("date");
        shed=intent.getStringExtra("shed");
        food_income=intent.getStringExtra("food_income");
        food_deposit=intent.getStringExtra("food_deposit");
        food_consumption=intent.getStringExtra("food_consumption");
        final_deposit=intent.getStringExtra("final_deposit");
        packagee=intent.getStringExtra("package_total");
        leftovereggs=intent.getStringExtra("leftover_eggs");
        chicken_death=intent.getStringExtra("chicken_death");
        observation=intent.getStringExtra("observation");
        recibido=intent.getStringExtra("Parametro");
        huevos_blancos=intent.getStringExtra("egg_white");
        huevos_rotos=intent.getStringExtra("egg_break");
        huevos_sucios=intent.getStringExtra("egg_dirty");
        group=intent.getStringExtra("grupo");

        Mostrar_Datos();
        verificar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Modificado Datos");
    }

    public String verificar(){
        if (verificacion_Arriva.equals(recibido)) {
            granja = "Granja_Arriva";
            return granja;
        }else{
            granja="Granja_Abajo";
            return granja;
        }
    }

    private void Mostrar_Datos(){
        mE_date.setText(date);
        mS_anterior.setText(food_income);
        mE_ingreso.setText(food_deposit);
        mConsumo.setText(food_consumption);
        mE_final.setText(final_deposit);
        mE_package.setText(packagee);
        mE_leftovereggs.setText(leftovereggs);
        mE_chickendeath.setText(chicken_death);
        mE_observation.setText(observation);
        mE_egg_white.setText(huevos_blancos);
        mE_egg_dirty.setText(huevos_sucios);
        mE_egg_break.setText(huevos_rotos);
    }

    private void updateDatos(String id,String shed,String date){
        String food_income=mS_anterior.getText().toString().trim();
        String food_deposit=mE_ingreso.getText().toString().trim();
        String food_consumption=mConsumo.getText().toString().trim();
        String final_deposit=mE_final.getText().toString().trim();
        String chicken_death=mE_chickendeath.getText().toString().trim();
        String package_total=mE_package.getText().toString().trim();
        String leftover_eggs=mE_leftovereggs.getText().toString().trim();
        String observation=mE_observation.getText().toString().trim();
        String huevos_blancos=mE_egg_white.getText().toString().trim();
        String huevos_sucios=mE_egg_dirty.getText().toString().trim();
        String huevos_rotos=mE_egg_break.getText().toString().trim();

        Call<Modelo> call=apiInterface.updateDatos("Token "+token_reci,id,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death,package_total,leftover_eggs,huevos_blancos,huevos_rotos,huevos_sucios,observation);
        Call<Modelo> calli=apiInterface.updateDatos1("Token "+token_reci,id,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death,package_total,leftover_eggs,huevos_blancos,huevos_rotos,huevos_sucios,observation);

        if (verificacion_Arriva.equals(recibido)) {
            call.enqueue(new Callback<Modelo>() {
                @Override
                public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Modificado Satisfactoriamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Ocurrio un error inesperado", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Modelo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Conexion a internet necesaria", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            calli.enqueue(new Callback<Modelo>() {
                @Override
                public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Modificado Satisfactoriamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Ocurrio un error inesperado", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Modelo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Conexion a internet necesaria", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void Boton_modificar(View v){
        Intent intentup = new Intent(Modificado_datos.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        intentup.putExtra("grupo",group);
        startActivity(intentup);
        updateDatos(id,shed,date);
        finish();
    }

    public void CancelUpdate(View v){
        Intent intentup = new Intent(Modificado_datos.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        intentup.putExtra("grupo",group);
        startActivity(intentup);
        finish();
    }
}
