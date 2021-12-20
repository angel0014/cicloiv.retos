package com.example.reto4b1.ui.servicios;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reto4b1.FormProductos;
import com.example.reto4b1.R;
import com.example.reto4b1.casos_uso.CasoUsoServicio;
import com.example.reto4b1.datos.DBHelper;
import com.example.reto4b1.modelos.Servicio;
import com.example.reto4b1.modelos.ServicioAdapter;

import java.util.ArrayList;




public class ServiciosFragment extends Fragment {
    private String TABLE_NAME = "SERVICIOS";
    private CasoUsoServicio casoUsoServicio;
    private ListView listView;
    private DBHelper dbHelper;
    private ArrayList<Servicio> servicios;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_servicios, container,false);
        try{
            casoUsoServicio = new CasoUsoServicio();
            dbHelper = new DBHelper(getContext());
            Cursor cursor = dbHelper.getData(TABLE_NAME);
            servicios = casoUsoServicio.llenarListaServicios(cursor);
            listView = (ListView) root.findViewById(R.id.lista_servicios);
            ServicioAdapter servicioAdapter = new ServicioAdapter(root.getContext(), servicios);
            listView.setAdapter(servicioAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_servicio:
                Intent intent = new Intent(getContext(), FormProductos.class);
                intent.putExtra("name","SERVICIOS");
                getActivity().startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}