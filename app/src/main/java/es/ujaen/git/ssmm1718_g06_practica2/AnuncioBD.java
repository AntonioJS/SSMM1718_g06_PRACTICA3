package es.ujaen.git.ssmm1718_g06_practica2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ajs00 on 07/01/2018.
 */

//Hereda de SQLiteOpenHelper para acceder a componentes de la base de datos..
public class AnuncioBD extends SQLiteOpenHelper {
    //Variables de nuestra base de datos.
    private static final String NOMBRE_BD="anuncio.bd";
    private static final int VERSION_BD=1;
    //Variable para almacenar datos del formulario de registro de anuncio.
    private static final String TABLA_ANUNCIOS="CREATE TABLE ANUNCIOS(ID TEXT PRIMARY KEY, NOMBRE TEXT,EMPRESA TEXT,DESCRIPCION TEXT)";

    //Constructor de nuestra clase.
    public AnuncioBD(Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);

    }
    //Creamos el método onCreate que se ejecutará automáticamente y creará
    //las tablas de nuestra base de datos.
    //Con execSQL ejecutamos lo requerido, en este caso la tabla de anuncios.
    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_ANUNCIOS);
    }


    //onUpgrade actualiza la base de datos si fuera encesario. En nuestro caso, decimos que la borre
    //si la tabla existe.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_ANUNCIOS);
        sqLiteDatabase.execSQL(TABLA_ANUNCIOS);

    }
//Método que agregará los registros a nuestra tabla.
    //Le asignamos los parámetros que ingresamos en el anuncio.
    public void agregarAnuncio(String id, String nombre, String empresa, String descripcion){
        //Para trabajar con la base de datos en escritura y lectura:
        SQLiteDatabase db = getWritableDatabase();
        if(db !=null){
            db.execSQL("INSERT INTO ANUNCIOS VALUES('"+id+"','"+nombre+"','"+empresa+"','"+descripcion+"')");
            //Cerramos base de datos.
            db.close();
        }
    }

}
