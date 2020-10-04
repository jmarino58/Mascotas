package com.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    protected ArrayList<Mascota> datosMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
        Toolbar customActionBar = findViewById(R.id.custom_action_bar);
        setSupportActionBar(customActionBar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        initData();
        RecyclerView recyclerViewListaMascotas = findViewById(R.id.recycler_view_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListaMascotas.setLayoutManager(layoutManager);
        MascotasAdapter adapter = new MascotasAdapter(datosMascotas);
        recyclerViewListaMascotas.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mascotas_menu_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
            case R.id.action_settings:
                return true;

            case R.id.action_favoritos:
                Intent intent = new Intent(this, MascotasFavoritas.class);

                intent.putParcelableArrayListExtra("ArrayList", datosMascotas);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void initData() {
        datosMascotas = new ArrayList<>();
        datosMascotas.add(new Mascota(R.drawable.cebra, getString(R.string.Cebra)));
        datosMascotas.add(new Mascota(R.drawable.conejo, getString(R.string.Conejo)));
        datosMascotas.add(new Mascota(R.drawable.elefante, getString(R.string.Elefante)));
        datosMascotas.add(new Mascota(R.drawable.leon, getString(R.string.Leon)));
        datosMascotas.add(new Mascota(R.drawable.jirafa, getString(R.string.Jirafa)));
        datosMascotas.add(new Mascota(R.drawable.gato, getString(R.string.Gato)));
        datosMascotas.add(new Mascota(R.drawable.mono, getString(R.string.Mono)));
        datosMascotas.add(new Mascota(R.drawable.panda, getString(R.string.Panda)));
        datosMascotas.add(new Mascota(R.drawable.perro, getString(R.string.Perro)));
        datosMascotas.add(new Mascota(R.drawable.simio, getString(R.string.Simio)));
        datosMascotas.add(new Mascota(R.drawable.tigre, getString(R.string.Tigre)));
    }
}