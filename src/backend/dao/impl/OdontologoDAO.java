package backend.dao.impl;

import backend.model.Odontologo;

import java.util.List;

public interface OdontologoDAO {
    void guardar(Odontologo odontologo);
    List<Odontologo> listarTodos();
}
