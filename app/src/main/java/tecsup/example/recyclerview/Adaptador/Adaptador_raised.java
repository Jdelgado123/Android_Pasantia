package tecsup.example.recyclerview.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tecsup.example.recyclerview.Modelos.Farm;
import tecsup.example.recyclerview.Modelos.Modelo_raised;
import tecsup.example.recyclerview.Modelos.Shed;
import tecsup.example.recyclerview.R;

public class Adaptador_raised extends RecyclerView.Adapter<Adaptador_raised.Entidad1> {

    private List<Modelo_raised> datos1;
    public Context context;
    private RecyclerViewClickListener_raised mListener;

public Adaptador_raised(List<Modelo_raised> datos1,Context context, RecyclerViewClickListener_raised listener) {
        this.datos1=datos1;
        this.context=context;
        this.mListener=listener;
        }

@Override
@NonNull
public Entidad1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_raised, parent, false);
        return new Entidad1(view,mListener);
        }

@Override
public void onBindViewHolder(final Entidad1 holder, int i) {
        holder.titulo.setText(datos1.get(i).getShed().getName());
        holder.contenido.setText(datos1.get(i).getFood_income());
        holder.text3.setText(datos1.get(i).getFood_deposit());
        holder.text4.setText(datos1.get(i).getFood_consumption());
        holder.text5.setText(datos1.get(i).getFinal_deposit());
        holder.text6.setText(datos1.get(i).getChicken_death());
        }

@Override
public int getItemCount() {
        return datos1.size();
        }

public class Entidad1 extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView titulo;
    TextView contenido,text3,text4,text5,text6;
    private LinearLayout mlistaItem_raised;

    public Entidad1(View itemView, Adaptador_raised.RecyclerViewClickListener_raised listener){
        super(itemView);
        titulo=itemView.findViewById(R.id.contenido_raised);
        contenido=itemView.findViewById(R.id.contenido1_raised);
        text3=itemView.findViewById(R.id.contenido2_raised);
        text4=itemView.findViewById(R.id.contenido3_raised);
        text5=itemView.findViewById(R.id.contenido4_raised);
        text6=itemView.findViewById(R.id.contenido5_raised);
        mlistaItem_raised=itemView.findViewById(R.id.listaitem_raised);
        mListener=listener;

        mlistaItem_raised.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listaitem_raised:
                mListener.onRowClick1(mlistaItem_raised, getAdapterPosition());
                break;
            default:
                break;
        }
    }
}
public interface RecyclerViewClickListener_raised {
    void onRowClick1(LinearLayout mlistaItem_raised, int i);
}
}
