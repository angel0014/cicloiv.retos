package com.example.reto4b1.ui.productos;

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

import com.example.reto4b1.R;
import com.example.reto4b1.casos_uso.CasoUsoProducto;

import com.example.reto4b1.datos.DBHelper;
import com.example.reto4b1.modelos.Producto;
import com.example.reto4b1.modelos.ProductoAdapter;

import java.util.ArrayList;



public class ProductosFragment extends Fragment {
    private String TABLE_NAME = "PRODUCTOS";
    private CasoUsoProducto casoUsoProducto;
    private ListView listView;
    private DBHelper dbHelper;
    private ArrayList<Producto> productos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_productos, container,false);
        try{
            casoUsoProducto = new CasoUsoProducto();
            dbHelper = new DBHelper(getContext());
            Cursor cursor = dbHelper.getData(TABLE_NAME);
            productos = casoUsoProducto.llenarListaProductos(cursor);
            listView = (ListView) root.findViewById(R.id.lista_productos);
            ProductoAdapter productoAdapter = new ProductoAdapter(root.getContext(), productos);
            listView.setAdapter(productoAdapter);
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
            case R.id.add_product:
                Intent intent = new Intent(getContext(), com.example.reto4b1.FormProductos.class);
                intent.putExtra("name","PRODUCTOS");
                getActivity().startActivity(intent);
                //Toast.makeText(getContext(), " Productos", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}