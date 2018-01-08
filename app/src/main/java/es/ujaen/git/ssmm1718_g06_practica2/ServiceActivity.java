package es.ujaen.git.ssmm1718_g06_practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ServiceActivity extends AppCompatActivity {
//Esta clase además es una actividad, la cuál pasaremos los datos introducidos por el usuario en el MainActivity.
//En este caso, como comprobación, imprimimos un get del dato user.En la siguiente práctica daremos uso a los parametros.
    public static final String PARAM_USER="param_user";
    public static final String PARAM_PASS="param_pass";
    public static final String PARAM_IP="param_ip";
    public static final String PARAM_PORT="param_port";

    //Parte p3 definiendo que hace boton
    Button btn_registrar;

    java.net.InetSocketAddress InetSocketAddress;
    SocketAddress socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //getSharedPreferences("sesion",0).getString("param_user","usuario");
        //String user=getIntent().getStringExtra(PARAM_USER);
        String user=getSharedPreferences("sesion",0).getString("param_user","usuario");
        String pass=getIntent().getStringExtra(PARAM_PASS);
        String ip=getIntent().getStringExtra(PARAM_IP);
        short port=getIntent().getShortExtra(PARAM_PORT,(short)6000);

        Toast.makeText(this,getString(R.string.welcome_login)+" "+user,Toast.LENGTH_SHORT).show();

        TextView title= (TextView) findViewById(R.id.txPerfil);
        title.setText(getString(R.string.welcome_login)+" "+user);

        //parte p3 que el boton haga algo
        Button btn_registrar = (Button) findViewById(R.id.botonnuevoanuncio);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //¿Qué queremos que ocurra?
                Intent intentReg = new Intent (ServiceActivity.this, Registro.class);
                ServiceActivity.this.startActivity(intentReg);
            }
        });



    }
}
