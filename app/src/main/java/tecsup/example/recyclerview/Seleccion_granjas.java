package tecsup.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tecsup.example.recyclerview.Modelos.Loginx;

public class Seleccion_granjas extends AppCompatActivity {

    public String Token_recibido,group,is_staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_granjas);

        Intent intent=this.getIntent();
        Token_recibido=intent.getStringExtra("tokencito");
        group=intent.getStringExtra("grupo");
        is_staff=intent.getStringExtra("staff");

    }
    public void Ingresar_granja_productionup(View v) {
        String granja="Granja_Arriva";
        Intent intentup = new Intent(Seleccion_granjas.this, MainActivity.class);
        intentup.putExtra("Parametro",granja);
        intentup.putExtra("tokencito",Token_recibido);
        intentup.putExtra("grupo",group);
        intentup.putExtra("staff",is_staff);
        Toast.makeText(getApplicationContext(), is_staff, Toast.LENGTH_SHORT).show();
        startActivity(intentup);
    }
    public void Ingresar_granja_productiondown(View v){
        String granja="Granja_Abajo";
        Intent intentdown=new Intent(Seleccion_granjas.this,MainActivity.class);
        intentdown.putExtra("Parametro",granja);
        intentdown.putExtra("tokencito",Token_recibido);
        intentdown.putExtra("grupo",group);
        intentdown.putExtra("staff",is_staff);
        startActivity(intentdown);
    }


}
