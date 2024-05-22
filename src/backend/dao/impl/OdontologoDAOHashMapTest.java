package backend.dao.impl;

import backend.dao.impl.OdontologoDAO;
import backend.model.Odontologo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OdontologoDAOHashMapTest {
    private OdontologoDAO odontologoDAO;

    @Before
    public void setUp() {
        odontologoDAO = new OdontologoDAOHashMap();
    }

    @Test
    public void testListarTodosDespuesDeGuardar() {
        Odontologo odontologo1 = new Odontologo(1,"1234", "Perez", "juan");
        Odontologo odontologo2 = new Odontologo(2,"5678", "Gomez", "maria");

        odontologoDAO.guardar(odontologo1);
        odontologoDAO.guardar(odontologo2);

        List<Odontologo> odontologos = odontologoDAO.listarTodos();
        assertEquals(2, odontologos.size());
    }
}
