package es.ujaen.git.ssmm1718_g06_practica2;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        SharedPreferences settings =this.getSharedPreferences("sesion",0);
        SharedPreferences.Editor editor=settings.edit();


        try {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH-mm-s");

            //Date fechaExp = formato.parse(sesion.getmExpires());
            String FechaExp = settings.getString("EXPIRES","0");
            Date fechaExp = formato.parse(FechaExp);

            Calendar c = Calendar.getInstance();

            String formateadaFechaActual = formato.format(c.getTime());
            //Date fechaActual = formato.parse(formateadaFechaActual);
            //String stringFechaConHora = ("2017-12-20-11-00-00");
            //Date fechaActual = formato.parse(stringFechaConHora);
            Date fechaActual = formato.parse(formateadaFechaActual);
            if(getSharedPreferences("sesion",0).contains("EXPIRES")==true) {
                if ((fechaExp.compareTo(fechaActual)) > 0) {


                    Toast.makeText(this, "Fecha expiracion OK", Toast.LENGTH_SHORT).show();
                    //Entonces recojo demas datos
                    settings.getString("param_user", "usuario");
                    Intent intent = new Intent(this, ServiceActivity.class);
                    startActivity(intent);
                    boolean estadoAuten = true;
                } else {
                    //clear() para borrar y commit() o apply() para confirmar lo anterior.
                    editor.clear().apply();

                    //startActivity(new Intent(getActivity(),LoginFragment.class));
                    //LLAMADA AL LOGIN
                    Toast.makeText(this, "Fecha expiracion OUT, vuelve a logear", Toast.LENGTH_SHORT).show();
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void onIcon(View vista) {
        TextView volatil = (TextView) findViewById(R.id.volatil);
        datosvolatiles = datosvolatiles.toUpperCase();
        volatil.setText(datosvolatiles);

    }
}