package ws;

import dominio.DistanciaImp;
import dto.Respuesta;
import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("distancia")
public class DistanciaWS {
    
    @Path("{origen}/{destino}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta calcularDistancia(@PathParam("origen") String origen,
                                       @PathParam("destino") String destino) {
        try {
            double resultado = DistanciaImp.obtenerDistancia(origen, destino);
            return new Respuesta(false, "Distancia calculada: " + resultado + " km");
        } catch (Exception e) {
            return new Respuesta(true, "Error al calcular distancia: " + e.getMessage());
        }
    }
        
}
