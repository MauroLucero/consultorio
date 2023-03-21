package com.integrador.consultorio.Repository.impl;

import com.integrador.consultorio.Repository.IDao;
import com.integrador.consultorio.Model.Odontologo;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDAOH2 implements IDao<Odontologo> {


    /* ----------- LOGGER -----------*/

    private static final Logger logger = Logger.getLogger(OdontologoIDAOH2.class);

    /* ----------- DEFINIMOS VARIABLES CON LOS DATOS DE CONEXION -----------*/

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";

    private final static String DB_URL = "jdbc:h2:~/integrador;INIT=RUNSCRIPT FROM 'classpath:odontologo.sql'";

    private final static String DB_USER ="sa";

    private final static String DB_PASSWORD = "";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {
            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            logger.info("Se guardo en la base de datos al odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " MP:" +odontologo.getMatricula());

            preparedStatement.execute();

            ResultSet resultPK=preparedStatement.getGeneratedKeys();
            while(resultPK.next()){

                odontologo.setId(resultPK.getLong("ID"));
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("No se pudo guardar el odontologo " + odontologo.getNombre() + " " + odontologo.getApellido(),throwables);


        }

        return odontologo;
    }

    @Override
    public Odontologo buscar(long id) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        Odontologo odontologo=null;



        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setLong(1,id);


            ResultSet result = preparedStatement.executeQuery();

           if (result.next()){

                long idOdontologo = result.getLong("ID");
                int matricula = result.getInt("MATRICULA");
                String nombre = result.getString("NOMBRE");
                String apellido = result.getString("APELLIDO");

                odontologo = new Odontologo(idOdontologo,matricula,nombre,apellido);
            }
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("No se encontro al odontologo con id= " + id,throwables);


        }
        logger.info("Se encontro al odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " MP:" + odontologo.getMatricula() + "con ID= " + id);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        Odontologo odontologo=null;

        List<Odontologo> lista = new ArrayList<>();



        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                long idOdontologo = result.getLong("ID");
                int matricula = result.getInt("MATRICULA");
                String nombre = result.getString("NOMBRE");
                String apellido = result.getString("APELLIDO");

                odontologo = new Odontologo(idOdontologo,matricula,nombre,apellido);
                lista.add(odontologo);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("No se pudo crear la lista",throwables);


        }
        logger.info("Se genero la lista de odontologos con éxito");
        return lista;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {
            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE ODONTOLOGOS SET matricula=?, nombre=?, apellido=? WHERE ID=?");
            preparedStatement.setInt(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.setLong(4,odontologo.getId());
            preparedStatement.execute();

            logger.info("Se actualizo en la base de datos al odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " MP:" +odontologo.getMatricula());



            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("No se pudo actualizar el odontologo " + odontologo.getNombre() + " " + odontologo.getApellido(),throwables);


        }
    }

    @Override
    public void eliminar(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID=?");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();



            preparedStatement.close();
        }
        catch (Exception e) {
            logger.error("Error al eliminar el domicilio",e);
            throw new RuntimeException(e);

        }
    }
}

