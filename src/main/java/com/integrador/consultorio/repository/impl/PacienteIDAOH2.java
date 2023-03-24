package com.integrador.consultorio.repository.impl;

import com.integrador.consultorio.repository.IDao;
import com.integrador.consultorio.entity.Domicilio;
import com.integrador.consultorio.entity.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteIDAOH2 implements IDao<Paciente> {
    /* ----------- LOGGER -----------*/

    private static final Logger logger = Logger.getLogger(OdontologoIDAOH2.class);

    /* ----------- DEFINIMOS VARIABLES CON LOS DATOS DE CONEXION -----------*/

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";

    private final static String DB_URL = "jdbc:h2:~/integrador;INIT=RUNSCRIPT FROM 'classpath:paciente.sql'";

    private final static String DB_USER ="sa";

    private final static String DB_PASSWORD = "";

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement=connection.prepareStatement("INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, ID_DOMICILIO) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,paciente.getNombre());
            preparedStatement.setString(2,paciente.getApellido());
            preparedStatement.setString(3,paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));


            Domicilio domicilio=paciente.getDomicilio();
            DomicilioIDAOH2 domicilioDAOH2=new DomicilioIDAOH2();


            domicilio=domicilioDAOH2.guardar(domicilio);


            preparedStatement.setLong(5,domicilio.getId());


            preparedStatement.execute();

            ResultSet resultPK=preparedStatement.getGeneratedKeys();
            while(resultPK.next()){
                paciente.setId(resultPK.getLong("ID"));
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return paciente;
    }

    @Override
    public Paciente buscar(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM paciente WHERE ID=?");
            preparedStatement.setLong(1,id);


            ResultSet resultado=preparedStatement.executeQuery();
            while(resultado.next()){
                paciente=new Paciente();
                paciente.setId(resultado.getLong("ID"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setApellido(resultado.getString("apellido"));
                paciente.setDni(resultado.getString("dni"));
                paciente.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());


                DomicilioIDAOH2 domicilioIDAOH2=new DomicilioIDAOH2();

                domicilio=domicilioIDAOH2.buscar(resultado.getLong("ID_DOMICILIO"));

                paciente.setDomicilio(domicilio);
            }

            logger.info("Se encontro al paciente con exito");
            preparedStatement.close();
        }
        catch (Exception e) {

            logger.error("No se pudo encontrar al paciente",e);
            throw new RuntimeException(e);
        }
        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        Paciente paciente=null;
        Domicilio domicilio=null;
        List<Paciente> listaPacientes= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM paciente");



            ResultSet resultado=preparedStatement.executeQuery();
            while(resultado.next()){
                paciente=new Paciente();
                paciente.setId(resultado.getLong("ID"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setApellido(resultado.getString("apellido"));
                paciente.setDni(resultado.getString("dni"));
                paciente.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());


                DomicilioIDAOH2 domicilioIDAOH2=new DomicilioIDAOH2();

                domicilio=domicilioIDAOH2.buscar(resultado.getLong("ID_DOMICILIO"));

                paciente.setDomicilio(domicilio);
                listaPacientes.add(paciente);
            }

            logger.info("Se listaron a los pacientes con éxito");
            preparedStatement.close();
        }
        catch (Exception e) {

            logger.error("No se pudo listar a los pacientes",e);
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    @Override
    public void actualizar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("UPDATE paciente SET nombre=?, apellido=?, dni=?, fecha_nacimiento=? WHERE ID=?");
            preparedStatement.setString(1,paciente.getNombre());
            preparedStatement.setString(2,paciente.getApellido());
            preparedStatement.setString(3,paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
            preparedStatement.setLong(5,paciente.getId());


            DomicilioIDAOH2 domicilioIDAOH2=new DomicilioIDAOH2();

            domicilioIDAOH2.actualizar(paciente.getDomicilio());

            logger.info("Se actualizao al paciente satisfactoriamente");
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (Exception e) {

            logger.error("No se pudo actualizar al paciente",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM paciente WHERE ID=?");
            preparedStatement.setLong(1,id);


            Paciente paciente=buscar(id);
            Domicilio domicilio=paciente.getDomicilio();
            DomicilioIDAOH2 domicilioIDAOH2=new DomicilioIDAOH2();

            domicilioIDAOH2.eliminar(domicilio.getId());

            logger.info("Se elimino el paciente satisfactoriamente");
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (Exception e) {

            logger.error("No se pudo eliminar al paciente",e);
            throw new RuntimeException(e);
        }
    }
}
