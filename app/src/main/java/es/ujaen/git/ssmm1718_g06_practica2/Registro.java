package es.ujaen.git.ssmm1718_g06_practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
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
        nuevaHebra();

    }

    private void nuevaHebra() {
        //Creamos una instancia de la clase Thread junto a un objeto Runnable.

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Lugar dónde realizar el proceso.

                //Ahora, creamos una instancia de nuestra clase creada anteriormente para así poder
                //hacer uso de los métodos de esta clase y mantener nuestra BD.

                //Creamos una instancia de la clase anuncioBD.

                final AnuncioBD anuncioBD = new AnuncioBD(getApplicationContext());

                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view){
                        //Llamamos a agregarAnuncio.
                        anuncioBD.agregarAnuncio(edtId.getText().toString(),edtNombre.getText().toString(),edtEmpresa.getText().toString(),edtDescripcion.getText().toString());
                        //Verificación.
                        Toast.makeText(getApplicationContext(),"SE AGREGÓ CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }).start();
    }
}
