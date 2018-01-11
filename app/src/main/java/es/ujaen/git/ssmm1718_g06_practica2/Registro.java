package es.ujaen.git.ssmm1718_g06_practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

  /********CÓDIGO ANTERIOR A TUTORÍA. ********

    //Declaramos los EditText (y el botón) para nuestro formulario de publicación de anuncio.
    EditText edtId,edtNombre,edtEmpresa,edtDescripcion;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
//Asociamos con los elementos de nuestro formulario de publicación de anuncio.
        edtId=(EditText)findViewById(R.id.edtID);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtEmpresa=(EditText)findViewById(R.id.edtEmpresa);
        edtDescripcion=(EditText)findViewById(R.id.edtDescripcion);

        btnAgregar=(Button)findViewById(R.id.btnAgregar);

//Creamos un método para crear la nueva hebra de trabajo de la actividad.
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view){

                nuevaHebra();
            }
        });
       // nuevaHebra();

    }

    private void nuevaHebra() {
        //Creamos una instancia de la clase Thread junto a un objeto Runnable.
        final String p1=edtId.getText().toString();
        final String p2="",p3="",p4="";
        //,edtNombre.getText().toString(),edtEmpresa.getText().toString(),edtDescripcion.getText().toString()
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Lugar dónde realizar el proceso.

                //Ahora, creamos una instancia de nuestra clase creada anteriormente para así poder
                //hacer uso de los métodos de esta clase y mantener nuestra BD.

                //Creamos una instancia de la clase anuncioBD.

                final AnuncioBD anuncioBD = new AnuncioBD(getApplicationContext());

                //Llamamos a agregarAnuncio.
                anuncioBD.agregarAnuncio(p1,p2,p3,p4);
                //Verificación.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"SE AGREGÓ CORRECTAMENTE",Toast.LENGTH_SHORT).show();

                    }
                });
                anuncioBD.close();

            }
        }).start();
    }
    */
  // ********CÓDIGO TUTORÍA********

  //Declaramos los EditText (y el botón) para nuestro formulario de publicación de anuncio.
  EditText edtId,edtNombre,edtEmpresa,edtDescripcion;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
//Asociamos con los elementos de nuestro formulario de publicación de anuncio.
        edtId=(EditText)findViewById(R.id.edtID);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtEmpresa=(EditText)findViewById(R.id.edtEmpresa);
        edtDescripcion=(EditText)findViewById(R.id.edtDescripcion);

        btnAgregar=(Button)findViewById(R.id.btnAgregar);

//Creamos un método para crear la nueva hebra de trabajo de la actividad.
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view){

                nuevaHebra();
            }
        });
        // nuevaHebra();

    }

    private void nuevaHebra() {
        //Creamos una instancia de la clase Thread junto a un objeto Runnable.
        final String id=edtId.getText().toString();
        final String nombre=edtNombre.getText().toString();
        final String empresa=edtEmpresa.getText().toString();
        final String descripcion=edtDescripcion.getText().toString();
        //,edtNombre.getText().toString(),edtEmpresa.getText().toString(),edtDescripcion.getText().toString()
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Lugar dónde realizar el proceso.

                //Ahora, creamos una instancia de nuestra clase creada anteriormente para así poder
                //hacer uso de los métodos de esta clase y mantener nuestra BD.

                //Creamos una instancia de la clase anuncioBD.

                final AnuncioBD anuncioBD = new AnuncioBD(getApplicationContext());

                //Llamamos a agregarAnuncio.
                anuncioBD.agregarAnuncio(id,nombre,empresa,descripcion);
                //Verificación.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"SE AGREGÓ CORRECTAMENTE",Toast.LENGTH_SHORT).show();

                    }
                });
                anuncioBD.close();

            }
        }).start();
    }


}
