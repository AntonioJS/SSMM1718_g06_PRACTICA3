package es.ujaen.git.ssmm1718_g06_practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//Clase principal que inicia el launcher, donde usamos los datos volatiles y comprobamos el uso de salvar una instancia.


public class MainActivity extends AppCompatActivity {

    private String datosvolatiles = "Hola";

    private TextView volatil=null;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

            datosvolatiles = savedInstanceState.getString("volatil", datosvolatiles);
        }


        TextView volatil = (TextView) findViewById(R.id.volatil);
        volatil.setText(datosvolatiles);
    }

    public void onIcon(View vista) {
        TextView volatil = (TextView) findViewById(R.id.volatil);
        datosvolatiles = datosvolatiles.toUpperCase();
        volatil.setText(datosvolatiles);

    }
}