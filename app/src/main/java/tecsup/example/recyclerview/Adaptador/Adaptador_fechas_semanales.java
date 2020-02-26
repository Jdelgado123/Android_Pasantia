package tecsup.example.recyclerview.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tecsup.example.recyclerview.Modelos.Datos_Semanales;
import tecsup.example.recyclerview.R;

public class Adaptador_fechas_semanales extends RecyclerView.Adapter<Adaptador_fechas_semanales.Entidad>{

    private List<Datos_Semanales> datos3;
    public Context context;

    public Adaptador_fechas_semanales(List<Datos_Semanales> datos3,Context context) {
        this.datos3=datos3;
        this.context=context;
    }

    @Override
    @NonNull
    public Adaptador_fechas_semanales.Entidad onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_fecha_semanal, parent, false);
        return new Adaptador_fechas_semanales.Entidad(view);
    }

    @Override
    public void onBindViewHolder(final Adaptador_fechas_semanales.Entidad holder, int i) {
        int crj=datos3.get(i).getShedregister().size();
        int a=0;
        if (a<crj){
            holder.contenido1.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido1.setText("--/--/--");
        }
        a++;
        if (a<crj){
            holder.contenido2.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido2.setText("--/--/--");
        }
        a++;
        if (a<crj){
            holder.contenido3.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido3.setText("--/--/--");
        }
        a++;
        if (a<crj){
            holder.contenido4.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido4.setText("--/--/--");
        }
        a++;
        if (a<crj){
            holder.contenido5.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido5.setText("--/--/--");
        }
        a++;
        if (a<crj){
            holder.contenido6.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido6.setText("--/--/--");
        }
        a++;
        if (a<crj){
            holder.contenido7.setText(datos3.get(i).getShedregister().get(a).getDate().substring(datos3.get(i).getShedregister().get(a).getDate().length()-5));
        }else {
            holder.contenido7.setText("--/--/--");
        }
    }
    @Override
    public int getItemCount() {
        int a=1;
        return a;
    }

    public class Entidad extends RecyclerView.ViewHolder{
        TextView contenido1,contenido2,contenido3,contenido4,contenido5,contenido6,contenido7;
        LinearLayout mlistaItem_semanal;

        public Entidad(View itemView){
            super(itemView);
            contenido1=itemView.findViewById(R.id.contenido_fecha1);
            contenido2=itemView.findViewById(R.id.contenido_fecha2);
            contenido3=itemView.findViewById(R.id.contenido_fecha3);
            contenido4=itemView.findViewById(R.id.contenido_fecha4);
            contenido5=itemView.findViewById(R.id.contenido_fecha5);
            contenido6=itemView.findViewById(R.id.contenido_fecha6);
            contenido7=itemView.findViewById(R.id.contenido_fecha7);
            mlistaItem_semanal=itemView.findViewById(R.id.listaitem_semanal);
        }
    }
}
