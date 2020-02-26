package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import tecsup.example.recyclerview.Adaptador.Adaptador;
import tecsup.example.recyclerview.Adaptador.Adaptador_raised;
import tecsup.example.recyclerview.Interface.ApiInterface;
import tecsup.example.recyclerview.Modelos.Modelo;
import tecsup.example.recyclerview.Modelos.Modelo_raised;
import tecsup.example.recyclerview.Modelos.Shed;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewss;
    private RecyclerView mRecyclerViewss_raised;
    private ListView listalateral;
    private androidx.appcompat.widget.Toolbar toolbar;

    private RecyclerView.LayoutManager LayoutManager;
    private RecyclerView.LayoutManager layoutManager_raised;

    private List<Modelo> datosList;
    private Adaptador adaptador;

    private List<Modelo_raised> datosList_raised;
    private List<Modelo_raised> shed;
    private Adaptador_raised adaptador_raised;

    private String recibido,granja,token_reci,group,is_staff;
    private String verificacion_Arriva="Granja_Arriva";

    Adaptador.RecyclerViewClickListener listener;
    Adaptador_raised.RecyclerViewClickListener_raised listener1;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        mRecyclerViewss=findViewById(R.id.RecyclerViewss);
        mRecyclerViewss_raised=findViewById(R.id.RecyclerViewss_raised);
        listalateral=findViewById(R.id.lista_lateral);
        toolbar= findViewById(R.id.mi_toolbar);


        mRecyclerViewss.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerViewss_raised.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=this.getIntent();
        recibido=intent.getStringExtra("Parametro");
        token_reci=intent.getStringExtra("tokencito");
        group=intent.getStringExtra("grupo");
        is_staff=intent.getStringExtra("staff");


        String[] opciones_menu_lateral={"Reportes Huevos","Salir"};
        ArrayAdapter<String> adapter_lateral= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                opciones_menu_lateral);

        listalateral.setAdapter(adapter_lateral);
        listalateral.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView < ? > adapterView, View view, int i, long l){
                switch (i) {
                    case 0:
                        Intent llamaractividad = new Intent(getApplicationContext(), Datos_semanales_huevos.class);
                        llamaractividad.putExtra("tokencito",token_reci);
                        llamaractividad.putExtra("grupo",group);
                        startActivity(llamaractividad);
                        break;
                    case 1:
                        Intent llamaractivida = new Intent(getApplicationContext(), Seleccion_granjas.class);
                        llamaractivida.putExtra("tokencito",token_reci);
                        llamaractivida.putExtra("grupo",group);
                        startActivity(llamaractivida);
                        finish();
                        break;
                }
            }
        });
        if (verificacion_Arriva.equals(recibido)) {
            granja = "Granja_Arriva";
        }else{
            granja="Granja_Abajo";
        }
        listener=new Adaptador.RecyclerViewClickListener() {
            @Override
            public void onRowClick(LinearLayout mlistaItem, int position){
                if (group.equals("Adminstradores") || is_staff.equals("true")) {
                    Intent intent = new Intent(MainActivity.this, Modificado_datos.class);
                    intent.putExtra("id", datosList.get(position).getId());
                    intent.putExtra("shed", datosList.get(position).getShed().getId());
                    intent.putExtra("date", datosList.get(position).getDate());
                    intent.putExtra("food_income", datosList.get(position).getFood_income());
                    intent.putExtra("food_deposit", datosList.get(position).getFood_deposit());
                    intent.putExtra("food_consumption", datosList.get(position).getFood_consumption());
                    intent.putExtra("final_deposit", datosList.get(position).getFinal_deposit());
                    intent.putExtra("package_total", datosList.get(position).getPackage_total());
                    intent.putExtra("leftover_eggs", datosList.get(position).getLeftover_eggs());
                    intent.putExtra("chicken_death", datosList.get(position).getChicken_death());
                    intent.putExtra("egg_white", datosList.get(position).getEgg_white());
                    intent.putExtra("egg_break",datosList.get(position).getEgg_break());
                    intent.putExtra("egg_dirty",datosList.get(position).getEgg_dirty());
                    intent.putExtra("observation", datosList.get(position).getObservation());
                    intent.putExtra("tokencito", token_reci);
                    intent.putExtra("Parametro", granja);
                    intent.putExtra("grupo",group);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_SHORT).show();
                }
            }
        };
        listener1= new Adaptador_raised.RecyclerViewClickListener_raised() {
            @Override
            public void onRowClick1(LinearLayout mlistaItem_raised, int i) {
                if (group.equals("Adminstradores") || is_staff.equals("true")) {
                    Intent intent1 = new Intent(MainActivity.this, Modificado_datos_raised.class);
                    intent1.putExtra("id", datosList_raised.get(i).getId());
                    intent1.putExtra("date", datosList_raised.get(i).getDate());
                    intent1.putExtra("shed", datosList_raised.get(i).getShed().getId());
                    intent1.putExtra("food_income", datosList_raised.get(i).getFood_income());
                    intent1.putExtra("food_deposit", datosList_raised.get(i).getFood_deposit());
                    intent1.putExtra("food_consumption", datosList_raised.get(i).getFood_consumption());
                    intent1.putExtra("final_deposit", datosList_raised.get(i).getFinal_deposit());
                    intent1.putExtra("chicken_death", datosList_raised.get(i).getChicken_death());
                    intent1.putExtra("observation", datosList_raised.get(i).getObservation());
                    intent1.putExtra("tokencito", token_reci);
                    intent1.putExtra("Parametro", granja);
                    intent1.putExtra("grupo",group);
                    startActivity(intent1);
                }else {
                    Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_SHORT).show();
                }
            }
        };

        final FloatingActionsMenu menu=findViewById(R.id.Grupo_Action_Fab);
        FloatingActionButton fab=findViewById(R.id.floating1);
        FloatingActionButton fab1=findViewById(R.id.floating2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (group.equals("Adminstradores") || is_staff.equals("true")) {
                    Intent intent = new Intent(MainActivity.this, Creacion_Datos.class);
                    intent.putExtra("Parametro", granja);
                    intent.putExtra("tokencito", token_reci);
                    intent.putExtra("grupo",group);
                    startActivity(intent);
                    menu.collapse();
                    Toast.makeText(getApplicationContext(), group, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (group.equals("Adminstradores") || is_staff.equals("true")) {
                    Intent intent1 = new Intent(MainActivity.this, Creacion_raised.class);
                    intent1.putExtra("Parametro", granja);
                    intent1.putExtra("tokencito", token_reci);
                    intent1.putExtra("grupo",group);
                    startActivity(intent1);
                    menu.collapse();
                }else {
                    Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setSupportActionBar(toolbar);
    }

    public void getDatos(){
    Call<List<Modelo>> call=apiInterface.getDatos("Token "+token_reci);
    Call<List<Modelo>> calli=apiInterface.getDatos1("Token "+token_reci);

    System.out.println(token_reci);
    if (verificacion_Arriva.equals(recibido)){
        call.enqueue(new Callback<List<Modelo>>() {
        @Override
        public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
            if (response.isSuccessful()) {
                datosList = response.body();
                adaptador = new Adaptador(datosList, MainActivity.this, listener);
                mRecyclerViewss.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "Algo salio mal, contactar con los programadores", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailure(Call<List<Modelo>> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_LONG).show();
        }
    });
    }else{
        calli.enqueue(new Callback<List<Modelo>>() {
        @Override
        public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
            if (response.isSuccessful()) {
                datosList = response.body();
                adaptador = new Adaptador(datosList, MainActivity.this, listener);
                mRecyclerViewss.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();
            }else {
                Toast.makeText(getApplicationContext(), "Algo salio mal, contactar con los programadores", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailure(Call<List<Modelo>> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_LONG).show();
        }
    });

        }
    }

    public void getDatos1(){

        Call<List<Modelo_raised>> call2=apiInterface.getRaised("Token "+token_reci);
        Call<List<Modelo_raised>> call3=apiInterface.getRaised1("Token "+token_reci);

        if (verificacion_Arriva.equals(recibido)) {
            call2.enqueue(new Callback<List<Modelo_raised>>() {
                @Override
                public void onResponse(Call<List<Modelo_raised>> call, Response<List<Modelo_raised>> response) {
                    if (response.isSuccessful()) {
                        datosList_raised= response.body();
                        adaptador_raised = new Adaptador_raised(datosList_raised, MainActivity.this, listener1);
                        mRecyclerViewss_raised.setAdapter(adaptador_raised);
                        adaptador_raised.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algo salio mal, contactar con los programadores", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<Modelo_raised>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            call3.enqueue(new Callback<List<Modelo_raised>>() {
                @Override
                public void onResponse(Call<List<Modelo_raised>> call, Response<List<Modelo_raised>> response) {
                    if (response.isSuccessful()) {
                        datosList_raised = response.body();
                        adaptador_raised = new Adaptador_raised(datosList_raised, MainActivity.this, listener1);
                        mRecyclerViewss_raised.setAdapter(adaptador_raised);
                        adaptador_raised.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Algo salio mal, contactar con los programadores", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<Modelo_raised>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Conexion a internet necesaria", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDatos();
        getDatos1();
    }
}
