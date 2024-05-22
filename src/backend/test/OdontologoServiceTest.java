package backend.test;

import backend.dao.impl.OdontologoDAOH2;
import backend.model.Odontologo;
import backend.service.OdontologoService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/parcialBackend;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try{
                connection.close();
            }catch (SQLException e){
                LOGGER.error(e.getMessage());
            }
        }
    }


    @Test
    @DisplayName("Testear que un odontologo persiste en la base de datos")
    void testearOdontologoDB(){
        Odontologo odontologo = new Odontologo(1, "12", "Pepe", "Gonzalez");
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
        Odontologo odontologoPersistido = odontologoService.guardarOdontologo(odontologo);
        assertNotNull(odontologoPersistido);
    }

    @Test
    @DisplayName("Testear que se pueda mostrar todos los odontologos")
    void testearTodosOdontologos(){
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
        List<Odontologo> odontologosRecibidos = odontologoService.buscarTodos();
        assertEquals(3, odontologosRecibidos.size());
    }
}
