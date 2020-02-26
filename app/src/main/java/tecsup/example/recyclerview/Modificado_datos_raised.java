package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tecsup.example.recyclerview.Interface.ApiInterface;
import tecsup.example.recyclerview.Modelos.Modelo_raised;

public class Modificado_datos_raised extends AppCompatActivity {

    private EditText mS_anterior,mE_ingreso,mConsumo,mE_final,mE_chickendeath,mE_date;

    String id;
    String shed,food_income,food_deposit,food_consumption,final_deposit,chicken_death,date,observation;

    androidx.appcompat.widget.Toolbar toolbar;

    private String recibido,granja,token_reci,group;
    private String verificacion_Arriva="Granja_Arriva";
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificado_datos_raised);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        mS_anterior=findViewById(R.id.E_s_anterior_raised);
        mE_date=findViewById(R.id.E_date_raised);
        mE_ingreso=findViewById(R.id.E_ingreso_raised);
        mConsumo=findViewById(R.id.E_consumo_raised);
        mE_final=findViewById(R.id.E_s_final_raised);
        mE_chickendeath=findViewById(R.id.E_chicken_death_raised);
        toolbar=findViewById(R.id.mi_toolbar);

        Intent intent1=this.getIntent();
        token_reci=intent1.getStringExtra("tokencito");
        recibido=intent1.getStringExtra("Parametro");
        id=intent1.getStringExtra("id");
        date=intent1.getStringExtra("date");
        shed=intent1.getStringExtra("shed");
        food_income=intent1.getStringExtra("food_income");
        food_deposit=intent1.getStringExtra("food_deposit");
        food_consumption=intent1.getStringExtra("food_consumption");
        final_deposit=intent1.getStringExtra("final_deposit");
        chicken_death=intent1.getStringExtra("chicken_death");
        group=intent1.getStringExtra("grupo");

        MostrarDatosRaised();
        verificar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Modificado datos Levante");
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

    private void MostrarDatosRaised(){
        mE_date.setText(date);
        mS_anterior.setText(food_deposit);
        mE_ingreso.setText(food_income);
        mConsumo.setText(food_consumption);
        mE_final.setText(final_deposit);
        mE_chickendeath.setText(chicken_death);

    }

    private void Update_Raised(String id,String shed,String date){
        String food_income=mE_ingreso.getText().toString().trim();
        String food_deposit=mS_anterior.getText().toString().trim();
        String food_consumption=mConsumo.getText().toString().trim();
        String final_deposit=mE_final.getText().toString().trim();
        String chicken_death=mE_chickendeath.getText().toString().trim();

        Call<Modelo_raised> call=apiInterface.PutDatosRaised("Token "+token_reci,id,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death);
        Call<Modelo_raised> call1=apiInterface.PutDatosRaised1("Token "+token_reci,id,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death);
        if (verificacion_Arriva.equals(recibido)) {
            call.enqueue(new Callback<Modelo_raised>() {
                @Override
                public void onResponse(Call<Modelo_raised> call, Response<Modelo_raised> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Modificado Correcto", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Modelo_raised> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Conexión a Internet necesaria", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            call1.enqueue(new Callback<Modelo_raised>() {
                @Override
                public void onResponse(Call<Modelo_raised> call, Response<Modelo_raised> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Modificado Correcto", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Modelo_raised> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Conexión a Internet necesaria", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Boton_modificar_Raised(View v){
        Intent intentup = new Intent(Modificado_datos_raised.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        intentup.putExtra("grupo",group);
        startActivity(intentup);
        Update_Raised(id,shed,date);
        finish();
    }

    public void CancelUpdate_Raised(View v){
        Intent intentup = new Intent(Modificado_datos_raised.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        intentup.putExtra("grupo",group);
        startActivity(intentup);
        finish();
    }
}
