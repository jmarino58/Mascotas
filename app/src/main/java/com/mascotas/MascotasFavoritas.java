package com.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {
    private ArrayList<Mascota> datosMascotas;
    private ArrayList<Mascota> mascotasFavoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);
        Toolbar customActionBar = findViewById(R.id.custom_action_bar);
        setSupportActionBar(customActionBar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        // Toma los datos del Activity Padre
        datosMascotas = getIntent().getParcelableArrayListExtra("ArrayList");

        initData();
        RecyclerView recyclerViewListaMascotas = findViewById(R.id.recycler_view_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListaMascotas.setLayoutManager(layoutManager);
        MascotasAdapter adapter = new MascotasAdapter(mascotasFavoritas);
        recyclerViewListaMascotas.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this,getIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void initData() {
        // Creamos un nuevo ArrayList para guardar la lista ordenada
        mascotasFavoritas = new ArrayList<>();
        // agregamos un objeto a la lista ordenada
        mascotasFavoritas.add(datosMascotas.get(0));
        boolean set;
        // se recorre el dataset
        for (int firstList = 1; firstList < datosMascotas.size(); firstList++) {
            set = false;
            for (int secondList = 0; (secondList < mascotasFavoritas.size() && !set); secondList++) {
                // se realiza la evaluación para ordenar los elementos de la lista favoritos
                //de mayor a menor puntaje
                if (datosMascotas.get(firstList).getCalificacion() >= mascotasFavoritas.get(secondList).getCalificacion()) {
                    mascotasFavoritas.add(secondList, datosMascotas.get(firstList));
                    set = true;
                }
            }
            if (!set) mascotasFavoritas.add(datosMascotas.get(firstList));
        }
            mascotasFavoritas.subList(5, mascotasFavoritas.size()).clear();
    }
}