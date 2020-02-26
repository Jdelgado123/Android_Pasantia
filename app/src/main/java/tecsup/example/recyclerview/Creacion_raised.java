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
import tecsup.example.recyclerview.Modelos.Modelo_raised;
import tecsup.example.recyclerview.Modelos.Shed;

import static android.R.layout.simple_spinner_dropdown_item;

public class Creacion_raised extends AppCompatActivity {

    private EditText mS_anterior,mE_ingreso,mConsumo,mE_final,mE_chickendeath,mE_date,mE_observation;
    Calendar myCalendar = Calendar.getInstance();
    private String shed;
    private Spinner mE_shed;
    private String granja,recibido,token_reci;;
    private String verificacion_Arriva="Granja_Arriva";
    androidx.appcompat.widget.Toolbar toolbar;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_raised);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Intent intent=this.getIntent();
        recibido=intent.getStringExtra("Parametro");
        token_reci=intent.getStringExtra("tokencito");
        toolbar=findViewById(R.id.mi_toolbar);

        if (verificacion_Arriva.equals(recibido)) {
            granja = "Granja_Arriva";

        }else{
            granja="Granja_Abajo";
        }

        mE_shed = findViewById(R.id.C_sheed_raised);
        mS_anterior=findViewById(R.id.C_s_anterior_raised);
        mE_ingreso=findViewById(R.id.C_ingreso_raised);
        mConsumo=findViewById(R.id.C_consumo_raised);
        mE_final=findViewById(R.id.C_s_final_raised);
        mE_chickendeath=findViewById(R.id.C_chicken_death_raised);
        mE_observation=findViewById(R.id.C_observation_raised);
        mE_date=findViewById(R.id.C_date_raised);
        mE_date.setFocusableInTouchMode(false);
        mE_date.setFocusable(false);
        mE_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Creacion_raised.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        getdatos();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nuevo Galpon de Levante");
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


    private void poblarSpinner(final List<Shed> shed_list){
        String name,id;
        final ArrayList<String> list=new ArrayList<>();
        for (Shed shed: shed_list) {
            if (verificacion_Arriva.equals(recibido) && shed.getFarm().getName().equals("Arriba") && shed.getName().contains("L")) {
                id = shed.getId();
                name = shed.getName();
                String spinner = id + " " + name;
                list.add(spinner);
            }else if (!verificacion_Arriva.equals(recibido) && shed.getFarm().getName().equals("Abajo") && shed.getName().contains("L")){
                id = shed.getId();
                name = shed.getName();
                String spinner = id + " " + name;
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
                    assert modelo_raiseds != null;
                    poblarSpinner(modelo_raiseds);
                }else{
                    Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Shed>> call, Throwable t) {

            }

        });
    }

    private void PostDatosRaised(){
        String date=mE_date.getText().toString().trim();
        String food_income=mE_ingreso.getText().toString().trim();
        String food_deposit=mS_anterior.getText().toString().trim();
        String food_consumption=mConsumo.getText().toString().trim();
        String final_deposit=mE_final.getText().toString().trim();
        String chicken_death=mE_chickendeath.getText().toString().trim();
        String observation=mE_observation.getText().toString().trim();

        Call<Modelo_raised> call=apiInterface.PostDatosRaised("Token "+token_reci,shed,date,food_income,food_deposit,food_consumption,final_deposit,chicken_death,observation);

        call.enqueue(new Callback<Modelo_raised>() {
            @Override
            public void onResponse(Call<Modelo_raised> call, Response<Modelo_raised> response) {
                Log.i(Creacion_raised.class.getSimpleName(), response.toString());
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Post Exitoso Raised", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Modelo_raised> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CancelarRaised(View view){
        Intent intentup = new Intent(Creacion_raised.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        startActivity(intentup);
        finish();
    }

    public void Guardar_DatosRaised(View view){
        Intent intentup = new Intent(Creacion_raised.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",token_reci);
        startActivity(intentup);
        PostDatosRaised();
        finish();
    }
}
