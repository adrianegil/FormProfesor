package com.proyecto.gilsoft.formprofesor.servicios;

import com.proyecto.gilsoft.formprofesor.modelo.OpcnPregVerdFalso;

/**
 * Created by Adrian Gil on 21/6/2019.
 */

public class LocalizacionServicios {


    // PATRÓN SINGLETÓN
    private static LocalizacionServicios servicios;

    public static LocalizacionServicios getServicios() {

        if (servicios == null)
            servicios = new LocalizacionServicios();

        return servicios;

    }

    // SERVICIOS

    public FormularioServicios formularioServicios;
    public PreguntaServicios preguntaServicios;
    public PregNumericaServicios pregNumericaServicios;
    public PregSeleccionServicios pregSeleccionServicios;
    public PregVerdFalsoServicios pregVerdFalsoServicios;
    public RespSeleccionServicios respSeleccionServicios;
    public OpcPregVerdFalsoServicios opcPregVerdFalsoServicios;


    public LocalizacionServicios() {

        formularioServicios = new FormularioServicios();
        preguntaServicios = new PreguntaServicios();
        pregNumericaServicios = new PregNumericaServicios();
        pregSeleccionServicios = new PregSeleccionServicios();
        pregVerdFalsoServicios = new PregVerdFalsoServicios();
        respSeleccionServicios = new RespSeleccionServicios();
        opcPregVerdFalsoServicios = new OpcPregVerdFalsoServicios();


    }


}
