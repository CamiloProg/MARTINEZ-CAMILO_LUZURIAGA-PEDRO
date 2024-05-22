package backend.service;

import backend.dao.IDao;
import backend.model.Odontologo;

import java.util.List;
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardarOdontologo(odontologo);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoIDao.listarOdontologos();
    }

}
