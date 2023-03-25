package com.integrador.consultorio.repository.impl;

import com.integrador.consultorio.repository.IDao;
import com.integrador.consultorio.entity.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TurnoIDAO implements IDao<Turno> {

    /* ----------- LOGGER -----------*/

    private static final Logger logger = Logger.getLogger(OdontologoIDAOH2.class);

    /* ----------- DEFINIMOS VARIABLES CON LOS DATOS DE CONEXION -----------*/

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";

    private final static String DB_URL = "jdbc:h2:~/integrador;INIT=RUNSCRIPT FROM 'classpath:paciente.sql'";

    private final static String DB_USER ="sa";

    private final static String DB_PASSWORD = "";

    private List<Turno> turnos = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
       turnos.add(turno);
       return turno;
    }

    @Override
    public void eliminar(long id) {
        TurnoService ts = new TurnoService();
        ts.setTurnoService(new TurnoIDAO());

        turnos.remove(ts.buscar(id));
    }

    @Override
    public Turno buscar(long id) {
        for (Turno t: turnos) {
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnos;
    }

    @Override
    public void actualizar(Turno turno) {
        TurnoService ts = new TurnoService();
        ts.setTurnoService(new TurnoIDAO());

        turnos.remove(ts.buscar(turno.getId()));

        turnos.add(turno);
    }
}
