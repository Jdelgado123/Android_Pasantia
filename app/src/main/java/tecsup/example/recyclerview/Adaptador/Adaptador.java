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

import tecsup.example.recyclerview.Modelos.Modelo;
import tecsup.example.recyclerview.R;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Entidad> {

    private List<Modelo> datos;
    public Context context;
    private RecyclerViewClickListener mListener;

    public Adaptador(List<Modelo> datos, Context context, RecyclerViewClickListener listener) {
        this.datos=datos;
        this.context=context;
        this.mListener=listener;
    }

    @Override
    @NonNull
    public Entidad onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista, parent, false);
        return new Entidad(view,mListener);
    }

    @Override
    public void onBindViewHolder(final Entidad holder, int position) {
        holder.titulo.setText(datos.get(position).getShed().getName());
        holder.contenido.setText(datos.get(position).getFood_income());
        holder.text3.setText(datos.get(position).getFood_deposit());
        holder.text4.setText(datos.get(position).getFood_consumption());
        holder.text5.setText(datos.get(position).getFinal_deposit());
        holder.text6.setText(datos.get(position).getPackage_total());
        holder.text7.setText(datos.get(position).getLeftover_eggs());
        holder.text8.setText(datos.get(position).getChicken_death());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class Entidad extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titulo;
        TextView contenido,text3,text4,text5,text6,text7,text8;
        private LinearLayout mlistaItem;

        public Entidad(View itemView, RecyclerViewClickListener listener){
            super(itemView);
            titulo=itemView.findViewById(R.id.contenido);
            contenido=itemView.findViewById(R.id.contenido1);
            text3=itemView.findViewById(R.id.contenido2);
            text4=itemView.findViewById(R.id.contenido3);
            text5=itemView.findViewById(R.id.contenido4);
            text6=itemView.findViewById(R.id.contenido5);
            text7=itemView.findViewById(R.id.contenido6);
            text8=itemView.findViewById(R.id.contenido7);
            mlistaItem=itemView.findViewById(R.id.listaitem);
            mListener=listener;

            mlistaItem.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.listaitem:
                    mListener.onRowClick(mlistaItem, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }
    public interface RecyclerViewClickListener {
        void onRowClick(LinearLayout mlistaItem, int position);
    }
}
