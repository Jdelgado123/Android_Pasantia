package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tecsup.example.recyclerview.Adaptador.Adaptador;
import tecsup.example.recyclerview.Adaptador.Adaptador_datos_semanales;
import tecsup.example.recyclerview.Adaptador.Adaptador_datos_semanales_other;
import tecsup.example.recyclerview.Adaptador.Adaptador_fechas_semanales;
import tecsup.example.recyclerview.Adaptador.Adaptador_fechas_semanales_other;
import tecsup.example.recyclerview.Interface.ApiInterface;
import tecsup.example.recyclerview.Modelos.Datos_Semanales;
import tecsup.example.recyclerview.Modelos.Shedregister;

public class Datos_semanales_huevos extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;

    private RecyclerView mRecyclerViewss;
    private RecyclerView mRecyclerViewss_fecha;
    private RecyclerView mRecyclerViewss_other;
    private RecyclerView mRecyclerViewss_fecha_other;

    private RecyclerView.LayoutManager LayoutManager;
    private RecyclerView.LayoutManager LayoutManager_fechas;

    private RecyclerView.LayoutManager LayoutManager_other;
    private RecyclerView.LayoutManager LayoutManager_fecha_other;

    private Adaptador_datos_semanales adaptador_datos_semanales;
    private Adaptador_datos_semanales_other adaptador_datos_semanales_other;
    private Adaptador_fechas_semanales adaptador_fechas_semanales;
    private Adaptador_fechas_semanales_other adaptador_fechas_semanales_other;
    private String token_reci;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_semanales_huevos);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        mRecyclerViewss=findViewById(R.id.RecyclerViewss_semanal);
        mRecyclerViewss_fecha=findViewById(R.id.RecyclerViewss_fechas_semanal);
        mRecyclerViewss_other=findViewById(R.id.RecyclerViewss_semanal_other);
        mRecyclerViewss_fecha_other=findViewById(R.id.RecyclerViewss_fechas_semanal_other);
        toolbar=findViewById(R.id.mi_toolbar);

        LayoutManager=new LinearLayoutManager(this);
        mRecyclerViewss.setLayoutManager(LayoutManager);
        LayoutManager_fechas=new LinearLayoutManager(this);
        mRecyclerViewss_fecha.setLayoutManager(LayoutManager_fechas);

        LayoutManager_other=new LinearLayoutManager(this);
        mRecyclerViewss_other.setLayoutManager(LayoutManager_other);
        LayoutManager_fecha_other=new LinearLayoutManager(this);
        mRecyclerViewss_fecha_other.setLayoutManager(LayoutManager_fecha_other);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Produccion semanal");

        Intent intent=this.getIntent();
        token_reci=intent.getStringExtra("tokencito");

        getDatos();
        getDatos1();
    }

    public void getDatos(){

        Call<List<Datos_Semanales>> call=apiInterface.GetDatosSemanales("Token "+token_reci);

        call.enqueue(new Callback<List<Datos_Semanales>>() {
            @Override
            public void onResponse(Call<List<Datos_Semanales>> call, Response<List<Datos_Semanales>> response) {
                if(response.isSuccessful()){
                    List<Datos_Semanales> datos_semanales=response.body();
                    adaptador_datos_semanales=new Adaptador_datos_semanales(datos_semanales,Datos_semanales_huevos.this);
                    adaptador_fechas_semanales=new Adaptador_fechas_semanales(datos_semanales,Datos_semanales_huevos.this);
                    mRecyclerViewss.setAdapter(adaptador_datos_semanales);
                    mRecyclerViewss_fecha.setAdapter(adaptador_fechas_semanales);
                    adaptador_datos_semanales.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Datos_Semanales>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, contactar con los programadores", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void getDatos1(){

        Call<List<Datos_Semanales>> call=apiInterface.GetDatosSemanales1("Token "+token_reci);

        call.enqueue(new Callback<List<Datos_Semanales>>() {
            @Override
            public void onResponse(Call<List<Datos_Semanales>> call, Response<List<Datos_Semanales>> response) {
                if(response.isSuccessful()){
                    List<Datos_Semanales> datos_semanales1=response.body();
                    adaptador_datos_semanales_other=new Adaptador_datos_semanales_other(datos_semanales1,Datos_semanales_huevos.this);
                    adaptador_fechas_semanales_other=new Adaptador_fechas_semanales_other(datos_semanales1,Datos_semanales_huevos.this);
                    mRecyclerViewss_other.setAdapter(adaptador_datos_semanales_other);
                    mRecyclerViewss_fecha_other.setAdapter(adaptador_fechas_semanales_other);
                    adaptador_datos_semanales_other.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Datos_Semanales>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, contactar con los programadores", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
