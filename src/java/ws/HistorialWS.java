package ws;

import com.google.gson.Gson;
import dominio.HistorialImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.HistorialCambios;

@Path("historial")
public class HistorialWS {
    
    @Path("historial-completo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialCambios> obtenerTodo() {
        return HistorialImp.obtenerTodo();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try{
            HistorialCambios cambios = gson.fromJson(json, HistorialCambios.class);
            return HistorialImp.registrar(cambios); 
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizar-envio")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar (String json){
        Gson gson = new Gson();
        try{
            HistorialCambios cambios = gson.fromJson(json, HistorialCambios.class);
            return HistorialImp.actualizacionEstado(cambios);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
}
