package backend;

import org.apache.log4j.Logger;

public class Main {
    public static Logger LOGGER = Logger.getLogger(Main.class);
    public static String SQL_CREATE = "DROP TABLE IF EXISTS CUENTAS;" +
            "CREATE TABLE CUENTAS(ID INT AUTO_INCREMENT PRIMARY KEY, NROCUENTA INT NOT NULL," +
            "NOMBRE VARCHAR(50) NOT NULL, SALDO NUMERIC(7,2) NOT NULL)";
    public static String SQL_INSERT = "INSERT INTO CUENTAS VALUES (DEFAULT,?,?,?)";
    public static String SQL_SELECT = "SELECT * FROM CUENTAS";
    public static String SQL_UPDATE = "UPDATE CUENTAS SET SALDO=? WHERE NROCUENTA=?";

}
