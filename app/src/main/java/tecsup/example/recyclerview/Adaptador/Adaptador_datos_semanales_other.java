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
import tecsup.example.recyclerview.Modelos.Shedregister;
import tecsup.example.recyclerview.R;

public class Adaptador_datos_semanales_other extends RecyclerView.Adapter<Adaptador_datos_semanales_other.Entidad> {
    private List<Datos_Semanales> datos2;
    public Context context;

    public Adaptador_datos_semanales_other(List<Datos_Semanales> datos2, Context context) {
        this.datos2 = datos2;
        this.context = context;
    }

    @Override
    @NonNull
    public Adaptador_datos_semanales_other.Entidad onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_dato_semanal_other, parent, false);
        return new Adaptador_datos_semanales_other.Entidad(view);
    }

    @Override
    public void onBindViewHolder(final Entidad holder, int i) {
        holder.contenido.setText(datos2.get(i).getName());
        for (Shedregister s : datos2.get(i).getShedregister()){
            int a =0;
            int crj=datos2.get(i).getShedregister().size();
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
        for (Shedregister s : datos2.get(i).getShedregister()){
            int a =0;
            int crj=datos2.get(i).getShedregister().size();
            if (a<crj){
                holder.contenido1.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido1.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido2.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido2.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido3.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido3.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido4.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido4.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido5.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido5.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido6.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido6.setText("**");
            }
            a++;
            if (a<crj){
                holder.contenido7.setText(s.getPackage_total()+" - "+s.getLeftover_eggs());
            }else{
                holder.contenido7.setText("**");
            }
            a=0;

        }

         */

    }

    @Override
    public int getItemCount() {
        return datos2.size();
    }

    public class Entidad extends RecyclerView.ViewHolder {
        TextView contenido;
        TextView contenido1, contenido2, contenido3, contenido4, contenido5, contenido6, contenido7, contenido8, contenido9, contenido10, contenido11, contenido12, contenido13, contenido14;
        LinearLayout mlistaItem_semanal;

        public Entidad(View itemView) {
            super(itemView);
            contenido = itemView.findViewById(R.id.contenido_semanal_other);
            contenido1 = itemView.findViewById(R.id.contenido1_semanal_other);
            contenido2 = itemView.findViewById(R.id.contenido2_semanal_other);
            contenido3 = itemView.findViewById(R.id.contenido3_semanal_other);
            contenido4 = itemView.findViewById(R.id.contenido4_semanal_other);
            contenido5 = itemView.findViewById(R.id.contenido5_semanal_other);
            contenido6 = itemView.findViewById(R.id.contenido6_semanal_other);
            contenido7 = itemView.findViewById(R.id.contenido7_semanal_other);
            mlistaItem_semanal = itemView.findViewById(R.id.listaitem_semanal);
        }
    }
}
