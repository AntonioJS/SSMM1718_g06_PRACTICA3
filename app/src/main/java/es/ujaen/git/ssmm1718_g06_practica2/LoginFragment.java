package es.ujaen.git.ssmm1718_g06_practica2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.R.id.input;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
* Clase que usaremos como Fragmento y donde se introduciran y se recogeran los datos del usuario.
*
*
* */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param_ip";
    private static final String ARG_PARAM2 = "param_port";

    // TODO: Rename and change types of parameters
    private String mUser="";
    private String mPass="";

    //Inicialización del objeto de la clase Autentication
    private PersonalData mAutentica = new PersonalData("","");
    //Inicialización del objeto de la clase Sesion
    private Sesion sesion = new Sesion("","");


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @param pass Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String user, String pass) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getString(ARG_PARAM1);
            mPass = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se genera el layout en el View. R.id se utiliza para referenciar el parámetro de
        //usuario con su correspondiente campo de texto.
        View fragment = inflater.inflate(R.layout.fragment_login, container, false);
        Button connect = (Button) fragment.findViewById(R.id.button_login);
        final EditText user = (EditText) fragment.findViewById(R.id.editText_login_user);
        final EditText pass = (EditText) fragment.findViewById(R.id.editText2_login_password);
        final EditText ip = (EditText) fragment.findViewById(R.id.editText_login_direccion);
        final EditText port = (EditText) fragment.findViewById(R.id.editText_login_puerto);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s_user = user.getText().toString();
                String s_pass = pass.getText().toString();
                String s_ip = ip.getText().toString();
                String s_port = port.getText().toString();


                short port2 = 0;
                try {
                    port2 = Short.parseShort(s_port);
                } catch (java.lang.NumberFormatException ex) {
                    port2 = 6000;

                }
                //Creamos el objeto con su constructor para rellenarlo.

                final ConnectionUserData data = new ConnectionUserData(s_user, s_pass, s_ip, port2);
                boolean estadoAuten=false;
                try {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

                    Date fechaExp = formato.parse(sesion.getmExpires());


                    Calendar c = Calendar.getInstance();
                    //System.out.println("Current time => " + c.getTime());

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    String formateadaFechaActual = df.format(c.getTime());


                    Date fechaActual = formato.parse(formateadaFechaActual);

                    if(fechaExp.compareTo(fechaActual)<0){

                        System.out.println("Fecha actual mayor que fechaEXPIRACION");
                    estadoAuten=true;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Implementar segun estado de la fecha --> si hacer tarea asincrona o no...
                Autenticacion aut = new Autenticacion();
                try {
                    //Almaceno los parámetros en un objeto de la clase autentication
                    //final PersonalData a = new PersonalData(mAutentica.getUser(), mAutentica.getPass());
                    //Método para iniciar la tarea asíncrona con el objeto de la clase Autentication, devolviendo objeto de la clase sesion
                    sesion = aut.execute(data).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                //Llamo al método para establecer preferencias compartidas
                SharedPreferences settings = getActivity().getSharedPreferences("sesion", 0);
                SharedPreferences.Editor editor = settings.edit();
                //Almaceno en el editor el identificador de sesion y la fecha en la que expira
                editor.putString("SESION-ID", sesion.getmSessionId());
                editor.putString("EXPIRES", sesion.getmExpires());
                //Almaceno las preferencias compartidas
                //editor.commit();
                editor.apply();

                //Comprobar por consola/Android Monitor sin depurar...(println-->Equivalente a un Log.i())
                System.out.println("SESION-ID: " + sesion.getmSessionId());
                System.out.println("EXPIRES: " + sesion.getmExpires());


                Toast.makeText(getContext(), getString(R.string.welcome_login)+" " + s_user + " " + s_pass + " " + s_ip + " " + s_port, Toast.LENGTH_LONG).show();


                /*
                Intent nueva=new Intent(getActivity(),ServiceActivity.class);
                nueva.putExtra(ServiceActivity.PARAM_USER,data.getUser());
                nueva.putExtra("param pass",data.getPass());
                nueva.putExtra("param ip",data.getConnectionIP());
                nueva.putExtra("param port",data.getConnectionPort());
                startActivity(nueva);
                */

                TareaAutentica tarea= new TareaAutentica();
                tarea.execute(data);
            }
        });

        return fragment;
    }

    //Clase donde comprobamos que la autenticación ha sido correcta, y que existen parametros.
    public class TareaAutentica extends AsyncTask<ConnectionUserData, Void, String> {

        private ConnectionUserData data;

        protected String doInBackground(ConnectionUserData... params) {

            if (params != null)
                if (params.length >= 1)
                    data = params[0];
            return "OK";


        }



        /**
         *
         * @param result OK si la operacion fue correcta y si no, otro valor.
         * */


        //Primero comprobamos result y entonces introducimos los parametros en la nueva actividad.
        public void onPostExecute(String result){

            if(result.compareToIgnoreCase("OK")==0) {

                Intent nueva = new Intent(getActivity(), ServiceActivity.class);
                nueva.putExtra(ServiceActivity.PARAM_USER, data.getUser());
                nueva.putExtra("param_pass", data.getPass());
                nueva.putExtra("param_ip", data.getConnectionIP());
                nueva.putExtra("param_port", data.getConnectionPort());
                startActivity(nueva);
            }else
            {
                Toast.makeText(getContext(), getString(R.string.error_login) +" "+data.getUser(),Toast.LENGTH_LONG).show();

            }




        }


    }
}

