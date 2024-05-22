package backend.dao.impl;

import backend.dao.impl.OdontologoDAO;
import backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OdontologoDAOHashMap implements OdontologoDAO {
    private static final Logger logger = Logger.getLogger(OdontologoDAOHashMap.class);
    private final Map<Integer, Odontologo> odontologosMap = new HashMap<>();
    private int idCounter = 0;

    @Override
    public void guardar(Odontologo odontologo) {
        idCounter++;
        odontologo.setId(idCounter);
        odontologosMap.put(idCounter, odontologo);
        logger.info("Odontólogo guardado: " + odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>(odontologosMap.values());
        logger.info("Listado de todos los odontólogos: " + odontologos);
        return odontologos;
    }
}
