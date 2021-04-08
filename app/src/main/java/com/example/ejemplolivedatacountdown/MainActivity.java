package com.example.ejemplolivedatacountdown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejemplolivedatacountdown.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private int numero;
    private int segundos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // LLamamos al  View Model
        final ViewModelProvider provider = ViewModelProviders.of(this);
        viewModel = provider.get(MainActivityViewModel.class);
        //Creamos elobserver para los segunodos y le pasamos el valor
        viewModel.seconds.observe(this, new Observer() {

            @Override
            public void onChanged(Object o) {
                this.onChanged((Integer)o);
            }

            public final void onChanged(Integer it) {

               binding.seconds.setText(String.valueOf(it));
            }
        });
        //PAsamos el valor del boolean finish
        viewModel.getFinish().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                this.onChanged((Boolean)o);
            }
            public final void onChanged(Boolean it){// Cuando termine el contador
                if(it){
                    binding.milisegundos.setText("milisegundos");
                    Toast.makeText(MainActivity.this,"Finalizado",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Cuando le damos al boton de Empezar
        binding.bttStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pasamos el valor del textview de milisegundos y  comprobamos  que el valor cumple los  requisitos
                String milisegundos = binding.milisegundos.getText().toString();

                if (milisegundos.isEmpty() || milisegundos.length() < 4 || milisegundos.equals("milisegundos")) {
                    Toast.makeText(MainActivity.this, "Número invalido, tiene que ser superior a 1000", Toast.LENGTH_SHORT).show();

                } else {
                 viewModel.timerValue.setValue(Long.parseLong(milisegundos));
                 viewModel.empezarContador();

                }
            }
        });
        binding.bttFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String milisegundos = binding.milisegundos.getText().toString();
                if (milisegundos.isEmpty() || milisegundos.length() < 4 || milisegundos.equals("milisegundos")) {
                    Toast.makeText(MainActivity.this, "Tienes que iniciar el  contador", Toast.LENGTH_SHORT).show();

                } else {

                    binding.seconds.setText("0");
                    viewModel.finalizarContador();
                }
            }
        });

    }
}