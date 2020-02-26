package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tecsup.example.recyclerview.Interface.ApiInterface;
import tecsup.example.recyclerview.Modelos.Loginx;
import tecsup.example.recyclerview.Modelos.Users;

public class Login extends AppCompatActivity {

     private TextInputEditText username,password;
     public String token,group,is_staff;
     ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        username=findViewById(R.id.usu);
        password=findViewById(R.id.pass);
    }

    private void Comprobacion_datos(List<Users> user_list){
        String user=username.getText().toString().trim();
        for (Users users: user_list){
            if (users.getUsername().equals(user)) {
                System.out.println("aaaaaaaa"+users.getGroups().size());
                if (users.getGroups().size()==0) {
                    group = "Superuser";
                    is_staff = users.getIs_staff();
                }else {
                    group=users.getGroups().get(0).getName();
                    is_staff =users.getIs_staff();
                }
            }
        }
    }
    private void Verif_usuario(){
        Call<List<Users>> call=apiInterface.Users();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()){
                    List<Users>veriff=response.body();
                    assert veriff != null;
                    Comprobacion_datos(veriff);
                }else{
                    Toast.makeText(getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Conexión a Internet necesaria", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Login(){
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        Call<Loginx> call=apiInterface.Login(user,pass);
        call.enqueue(new Callback<Loginx>() {
            @Override
            public void onResponse(Call<Loginx> call, Response<Loginx> response) {
                if(response.isSuccessful()){
                    token=response.body().getToken();
                    Intent intentContactos = new Intent(Login.this,Seleccion_granjas.class);
                    intentContactos.putExtra("tokencito",token);
                    intentContactos.putExtra("grupo",group);
                    intentContactos.putExtra("staff",is_staff);
                    startActivity(intentContactos);
                    Toast.makeText(getApplicationContext(),"Bienvenido "+group, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Loginx> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Conexión a Internet necesaria", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean verificar(){
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        boolean validd=true;

        if (user.isEmpty()){
            username.setError("Campo Obligatorio");
            validd=false;
        }if (pass.isEmpty()){
            password.setError("Campo Obligatorio");
            validd=false;
        }
        return validd;
    }

    public void Ingresar(View v) {
        if (verificar()) {
            Verif_usuario();
            Login();
        }
    }
    public void Salir(View v){
        finish();
    }
}
