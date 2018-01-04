package es.ujaen.git.ssmm1718_g06_practica2;

/**
 * Created by usuario on 04/10/2017.
 */
/*
* Esta clase complementa a PersonalData añadiendo los datos por defecto de la conexión de nuestra aplicación
* que seran la ip y el puerto que necesitamos en caso de que no se introduzcan otros parametros.
*
* */
public class ConnectionUserData extends PersonalData {
    protected String connectionIP="127.0.0.1";
    protected short connectionPort=6000;


    public ConnectionUserData(String user,String pass,short port){
        super(user,pass);
        //this.connectionIP=ip;
        this.connectionPort=port;


    }

    public String getConnectionIP() {
        return connectionIP;
    }

    public void setConnectionIP(String connectionIP) {
        this.connectionIP = connectionIP;
    }

    public short getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(short connectionPort) {
        this.connectionPort = connectionPort;
    }
}
