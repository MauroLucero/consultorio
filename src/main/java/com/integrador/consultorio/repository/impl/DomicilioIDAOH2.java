package com.integrador.consultorio.repository.impl;

import com.integrador.consultorio.repository.IDao;
import com.integrador.consultorio.entity.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioIDAOH2 implements IDao<Domicilio> {
    /* ----------- LOGGER -----------*/

    private static final Logger logger = Logger.getLogger(OdontologoIDAOH2.class);

    /* ----------- DEFINIMOS VARIABLES CON LOS DATOS DE CONEXION -----------*/

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";

    private final static String DB_URL = "jdbc:h2:~/integrador;INIT=RUNSCRIPT FROM 'classpath:domicilio.sql'";

    private final static String DB_USER ="sa";

    private final static String DB_PASSWORD = "";

    @Override
    public Domicilio guardar(Domicilio domicilio) {

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("INSERT INTO DOMICILIO (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3,domicilio.getLocalidad());
            preparedStatement.setString(4,domicilio.getProvincia());
            preparedStatement.execute();

            ResultSet resultPK=preparedStatement.getGeneratedKeys();
            while(resultPK.next()){

                domicilio.setId(resultPK.getLong("ID"));
            }




            preparedStatement.close();
        } catch (Exception e) {
            logger.error("No se pudo guardar el domicilio ",e);
            throw new RuntimeException(e);
        }

        return domicilio;
    }



    @Override
    public void eliminar(long id) {

        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM DOMICILIO WHERE ID=?");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();



            preparedStatement.close();
        }
        catch (Exception e) {
            logger.error("Error al eliminar el domicilio",e);
            throw new RuntimeException(e);

        }


    }

    @Override
    public Domicilio buscar(long id) {

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        Domicilio domicilio= new Domicilio();

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM DOMICILIO WHERE ID=?");
            preparedStatement.setLong(1,id);

            ResultSet resultadoBusqueda=preparedStatement.executeQuery();
            if(resultadoBusqueda.next()){
                domicilio.setId(resultadoBusqueda.getLong("ID"));
                domicilio.setCalle(resultadoBusqueda.getString("CALLE"));
                domicilio.setNumero(resultadoBusqueda.getInt("NUMERO"));
                domicilio.setLocalidad(resultadoBusqueda.getString("LOCALIDAD"));
                domicilio.setProvincia(resultadoBusqueda.getString("PROVINCIA"));
            }



            preparedStatement.close();
        }
        catch (Exception e) {
            logger.error("Error al buscar el domicilio",e);
            throw new RuntimeException(e);

        }
        return domicilio;
    }

    @Override
    public List<Domicilio> listarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        Domicilio domicilio= new Domicilio();
        List<Domicilio> listaDomicilios= new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM DOMICILIO");


            ResultSet resultadoBusqueda=preparedStatement.executeQuery();
            while(resultadoBusqueda.next()){
                domicilio.setId(resultadoBusqueda.getLong("ID"));
                domicilio.setCalle(resultadoBusqueda.getString("CALLE"));
                domicilio.setNumero(resultadoBusqueda.getInt("NUMERO"));
                domicilio.setLocalidad(resultadoBusqueda.getString("LOCALIDAD"));
                domicilio.setProvincia(resultadoBusqueda.getString("PROVINCIA"));

                listaDomicilios.add(domicilio);
            }



            preparedStatement.close();
        }
        catch (Exception e) {
            logger.error("Error al listar los domicilio",e);
            throw new RuntimeException(e);

        }
        return listaDomicilios;
    }

    @Override
    public void actualizar(Domicilio domicilio) {

        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement=connection.prepareStatement("UPDATE DOMICILIO SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?");
            preparedStatement.setString(1,domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3,domicilio.getLocalidad());
            preparedStatement.setString(4,domicilio.getProvincia());
            preparedStatement.setLong(5,domicilio.getId());
            preparedStatement.execute();



            preparedStatement.close();
        }
        catch (Exception e) {
            logger.error("Error al actualizar el domicilio",e);
            throw new RuntimeException(e);

        }

    }




}
