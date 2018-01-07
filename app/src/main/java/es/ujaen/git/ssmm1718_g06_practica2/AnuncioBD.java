package es.ujaen.git.ssmm1718_g06_practica2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ajs00 on 07/01/2018.
 */

public class AnuncioBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="anuncio.bd";
    private static final int VERSION_BD=1;
    private static final String TABLA_ANUNCIOS="CREATE TABLE ANUNCIOS(ID TEXT PRIMARY KEY, NOMBRE TEXT,EMPRESA TEXT,DESCRIPCION TEXT)";

    public AnuncioBD(Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);

    }
    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_ANUNCIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_ANUNCIOS);
        sqLiteDatabase.execSQL(TABLA_ANUNCIOS);

    }

    public void agregarAnuncio(String id, String nombre, String empresa, String descripcion){
        SQLiteDatabase db = getWritableDatabase();
        if(db !=null){
            db.execSQL("INSERT INTO ANUNCIOS VALUES('"+id+"','"+nombre+"','"+empresa+"','"+descripcion+"')");
            db.close();
        }
    }

}
