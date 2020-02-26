package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tecsup.example.recyclerview.Interface.ApiInterface;
import tecsup.example.recyclerview.Modelos.Modelo;
import tecsup.example.recyclerview.Modelos.Shed;

import static android.R.layout.simple_spinner_dropdown_item;

public class Creacion_Datos extends AppCompatActivity{

    private Spinner mE_shed;
    public String shed;
    private EditText mE_ingreso,mS_anterior,mConsumo,mE_final,mE_chickendeath,mE_date,mE_package_total,mE_leftover_eggs,mE_observation,mE_egg_white,mE_egg_break,mE_egg_dirty;
    Calendar myCalendar = Calendar.getInstance();
    private String recibido,granja,token_reci,group;
    private String verificacion_Arriva="Granja_Arriva";
    androidx.appcompat.widget.Toolbar toolbar;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion__datos);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Intent intent=this.getIntent();
        recibido=intent.getStringExtra("Parametro");
        token_reci=intent.getStringExtra("tokencito");
        group=intent.getStringExtra("grupo");
        toolbar=findViewById(R.id.mi_toolbar);

        if (verificacion_Arriva.equals(recibido)) {
            granja = "Granja_Arriva";
        }else{
            granja="Granja_Abajo";
        }

        mE_shed=findViewById(R.id.C_sheed);
        mE_ingreso=findViewById(R.id.C_ingreso);
        mS_anterior=findViewById(R.id.C_s_anterior);
        mConsumo=findViewById(R.id.C_consumo);
        mE_final=findViewById(R.id.C_s_final);
        mE_chickendeath=findViewById(R.id.C_chicken_death);
        mE_package_total=findViewById(R.id.C_package_total);
        mE_leftover_eggs=findViewById(R.id.C_leftover_eggs);
        mE_egg_white=findViewById(R.id.C_huevos_vlancos);
        mE_egg_break=findViewById(R.id.C_huevos_rotos);
        mE_egg_dirty=findViewById(R.id.C_huevos_sucios);
        mE_observation=findViewById(R.id.C_observation);
        mE_date=findViewById(R.id.C_date);
        mE_date.setFocusableInTouchMode(false);
        mE_date.setFocusable(false);
        mE_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Creacion_Datos.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        getdatos();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nuevo Galpon");
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }
    };

    private void setBirth() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mE_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void poblarSpinner(List<Shed> shed_list){
        String id,name;
        final ArrayList<String> list=new ArrayList<>();
        for (Shed shed: shed_list) {
            if (verificacion_Arriva.equals(recibido) && shed.getFarm().getName().equals("Arriba") && shed.getName().contains("P")) {
                id = shed.getId();
                name = shed.getName();
                String spinner = id+" "+name;
                list.add(spinner);
            } else if (!verificacion_Arriva.equals(recibido) && shed.getFarm().getName().equals("Abajo") && shed.getName().contains("P")) {
                id = shed.getId();
                name = shed.getName();
                String spinner = id+" "+name;
                list.add(spinner);
            }
        }

        ArrayAdapter<String>spinnerAdapterSpinner = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,list);
        spinnerAdapterSpinner.setDropDownViewResource(simple_spinner_dropdown_item);
        mE_shed.setAdapter(spinnerAdapterSpinner);
        mE_shed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bla=adapterView.getItemAtPosition(i).toString();
                String[] arr = bla.split(" ");
                shed= arr[0];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getdatos(){

        Call<List<Shed>> call=apiInterface.PoblarSpinner("Token "+token_reci);

        call.enqueue(new Callback<List<Shed>>() {
            @Override
            public void onResponse(Call<List<Shed>> call, Response<List<Shed>> response) {
                if(response.isSuccessful()){
                    List<Shed> modelo_raiseds=response.body();
                    poblarSpinner(modelo_raiseds);
                }else{
                    Toast.makeText(getApplicationContext(), "no hay datos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Shed>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void PostDatos(){
        String date=mE_date.getText().toString().trim();
        String food_income=mE_ingreso.getText().toString().trim();
        String food_deposit=mS_anterior.getText().toString().trim();
        String food_consumption=mConsumo.getText().toString().trim();
        String final_deposit=mE_final.getText().toString().trim();
        String chicken_death=mE_chickendeath.getText().toString().trim();
        String package_total=mE_package_total.getText().toString().trim();
        String leftover_eggs=mE_leftover_eggs.getText().toString().trim();
        String observation=mE_observation.getText().toString().trim();
        String huevo_blanco=mE_egg_white.getText().toString().trim();
        String huevo_roto=mE_egg_break.getText().toString().trim();
        String huevo_sucio=mE_egg_dirty.getText().toString().trim();

        Call<Modelo> call=apiInterface.postDatos("Token "+token_reci,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death,package_total,leftover_eggs,huevo_blanco,huevo_roto,huevo_sucio,observation);
        Call<Modelo> calli=apiInterface.postDatos1("Token "+token_reci,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death,package_total,leftover_eggs,huevo_blanco,huevo_roto,huevo_sucio,observation);

        if (verificacion_Arriva.equals(recibido)) {
            call.enqueue(new Callback<Modelo>() {
                @Override
                public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                    Log.i(Creacion_Datos.class.getSimpleName(), response.toString());
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Post Exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Modelo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            calli.enqueue(new Callback<Modelo>() {
                @Override
                public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                    Log.i(Modificado_datos.class.getSimpleName(), response.toString());
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Post Exitososo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Modelo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void CancelarProduction(View view){
        Intent intentup = new Intent(Creacion_Datos.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        intentup.putExtra("grupo",group);
        startActivity(intentup);
        finish();
    }

    public void Guardar_Datos(View view){
        Intent intentup = new Intent(Creacion_Datos.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        intentup.putExtra("grupo",group);
        startActivity(intentup);
        PostDatos();
        finish();
    }
}
