package es.ujaen.git.ssmm1718_g06_practica2;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

/**
 * Created by usuario on 15/11/2017.
 */

public class Autenticacion extends AsyncTask<ConnectionUserData,Void,Sesion> {

    @Override
    protected void onPreExecute() {

        Autenticacion datos=null;
        super.onPreExecute();
    }

    @Override
    protected Sesion doInBackground(ConnectionUserData... params) {

        Sesion sesion= new Sesion("","");

        try {
            //Usuario y contraseña de ConnectionUserData
            String mUser = params[0].user;
            String mPass = params[0].pass;

            //URL del servidor
            URL url = new URL("http://www4.ujaen.es");

            //Inicio el socket
            Socket socket = new Socket(url.getHost(), 80);

            //Inicio de las clases leer y escribir
            OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
            InputStreamReader is = new InputStreamReader(socket.getInputStream());

            /*Comprobacion en telnet de la peticion GET:
            GET /~jccuevas/ssmm/autentica.php?user=user&pass=123456 HTTP/1.1
            host:www4.ujaen.es
            */

            //Escribo para enviar al socket
            os.write(new String("GET /~jccuevas/ssmm/autentica.php?user=" + mUser + "&pass=" + mPass + " HTTP/1.1\r\nhost:www4.ujaen.es\r\n\r\n"));
            os.flush();

            //Clase para almacenar en buffer
            BufferedReader in = new BufferedReader(is);

            //Inicio variables que voy a utilizar
            String inputline; //Línea de entrada
            String [] linea = null; //Línea donde almaceno la entrada en cadenas, separadas por &
            String [] sessionid =  null; //Id de sesion
            String [] expires = null; //Fecha en la que expira la sesion

            //Recibo respuesta HTTP...
            while ((inputline = in.readLine()) != null) {
                //Donde encuentre &... me quedo con la linea/cadena
                linea = inputline.split("&");
            }
            //Quitar cabecera de id sesion
            sessionid = linea[0].split("SESION-ID=");
            //Quitar cabecera de fecha
            expires = linea[1].split("EXPIRES=");

            //Set en la clase sesion
            sesion.setmSessionId(sessionid[1]);
            sesion.setmExpires(expires[1]);

            //Cierre clase leer y escribir
            os.close();
            in.close();

            //Cierre sock
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sesion;
    }

}
