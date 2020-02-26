package tecsup.example.recyclerview.Adaptador;
import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import tecsup.example.recyclerview.Modelos.Datos_Semanales;
import tecsup.example.recyclerview.Modelos.Shedregister;
import tecsup.example.recyclerview.R;

public class Adaptador_datos_semanales extends RecyclerView.Adapter<Adaptador_datos_semanales.Entidad> {
    private List<Datos_Semanales> datos2;
    public List<Shedregister> shedregisters;
    public Context context;

    public Adaptador_datos_semanales(List<Datos_Semanales> datos2,Context context) {
        this.datos2=datos2;
        this.context=context;
    }

    @Override
    @NonNull
    public Adaptador_datos_semanales.Entidad onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_dato_semanal, parent, false);
        return new Adaptador_datos_semanales.Entidad(view);
    }

    @Override
    public void onBindViewHolder(final Entidad holder, int i) {
        holder.contenido.setText(datos2.get(i).getName());
        for (Shedregister s : datos2.get(i).getShedregister()){
            int a =0;
            int crj=datos2.get(i).getShedregister().size();
            System.out.println("cucucucucu"+crj);
            if (a<crj){
                holder.contenido1.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido1.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido2.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido2.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido3.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido3.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido4.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido4.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido5.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido5.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido6.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido6.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido7.setText(datos2.get(i).getShedregister().get(a).getPackage_total()+"-"+datos2.get(i).getShedregister().get(a).getLeftover_eggs());
            }else{
                holder.contenido7.setText("**");
            }
        }
            /*
            holder.contenido1.setText(datos2.get(i).getShedregister().get(0).getPackage_total());
            holder.contenido2.setText(datos2.get(i).getShedregister().get(0).getLeftover_eggs());
            holder.contenido3.setText(datos2.get(i).getShedregister().get(1).getPackage_total());
            holder.contenido4.setText(datos2.get(i).getShedregister().get(1).getLeftover_eggs());
            holder.contenido5.setText(datos2.get(i).getShedregister().get(2).getPackage_total());
            holder.contenido6.setText(datos2.get(i).getShedregister().get(2).getLeftover_eggs());
            holder.contenido7.setText(datos2.get(i).getShedregister().get(3).getPackage_total());
            holder.contenido8.setText(datos2.get(i).getShedregister().get(3).getLeftover_eggs());
            holder.contenido9.setText(datos2.get(i).getShedregister().get(4).getPackage_total());
            holder.contenido10.setText(datos2.get(i).getShedregister().get(4).getLeftover_eggs());
            holder.contenido11.setText(datos2.get(i).getShedregister().get(5).getPackage_total());
            holder.contenido12.setText(datos2.get(i).getShedregister().get(5).getLeftover_eggs());
            holder.contenido13.setText(datos2.get(i).getShedregister().get(6).getPackage_total());
            holder.contenido14.setText(datos2.get(i).getShedregister().get(6).getLeftover_eggs());
*/
    }
    @Override
    public int getItemCount() {
        return datos2.size();
    }

    public class Entidad extends RecyclerView.ViewHolder{
        TextView contenido;
        TextView contenido1,contenido2,contenido3,contenido4,contenido5,contenido6,contenido7,contenido8,contenido9,contenido10,contenido11,contenido12,contenido13,contenido14;
        LinearLayout mlistaItem_semanal;

        public Entidad(View itemView){
            super(itemView);
            contenido=itemView.findViewById(R.id.contenido_semanal);
            contenido1=itemView.findViewById(R.id.contenido1_semanal);
            contenido2=itemView.findViewById(R.id.contenido2_semanal);
            contenido3=itemView.findViewById(R.id.contenido3_semanal);
            contenido4=itemView.findViewById(R.id.contenido4_semanal);
            contenido5=itemView.findViewById(R.id.contenido5_semanal);
            contenido6=itemView.findViewById(R.id.contenido6_semanal);
            contenido7=itemView.findViewById(R.id.contenido7_semanal);
            mlistaItem_semanal=itemView.findViewById(R.id.listaitem_semanal);
        }
    }

}