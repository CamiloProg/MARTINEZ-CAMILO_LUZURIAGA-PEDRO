package backend.dao.impl;

import backend.dao.IDao;
import backend.db.H2Connection;
import backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {
    private static Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);
    private static String SQL_INSERT = "INSERT INTO odontologos VALUES (DEFAULT, ?, ?, ?)";
    private static String SQL_GET = "SELECT * FROM odontologos";



    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologo1 = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                odontologo1 = new Odontologo(id, odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
                LOGGER.info("Odontologo persisitido " + odontologo1);
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (Exception e){
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.info(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologo1;
    }


    @Override
    public List<Odontologo> listarOdontologos() {
        Connection connection = null;
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET);

            while (resultSet.next()) {
                odontologo = new Odontologo(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                odontologos.add(odontologo);
            }
            LOGGER.info("Odontologos: " + odontologos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologos;
    }
}

