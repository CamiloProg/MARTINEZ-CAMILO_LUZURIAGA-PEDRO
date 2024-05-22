package backend.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import backend.model.Odontologo;

public class OdontologoDaoCollection implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoCollection.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        odontologos.add(odontologo);
        logger.info("Odontologo registrado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> obtenerTodos() {
        logger.info("Listado de todos los odontologos: " + odontologos);
        return new ArrayList<>(odontologos);
    }
}
