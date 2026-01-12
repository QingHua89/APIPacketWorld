package ws;

import com.google.gson.Gson;
import dominio.ClienteImp;
import dominio.ColaboradorImp;
import dominio.PaqueteImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.Paquete;


@Path("paquete")
public class PaqueteWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerTodos() {
        return PaqueteImp.obtenerPaquetes();
    }
    
    @Path("nuevo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try{
            Paquete paquete = gson.fromJson(json, Paquete.class);
            return PaqueteImp.registrar(paquete); 
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar(String json){
        Gson gson = new Gson();
        try{
            Paquete paquete = gson.fromJson(json, Paquete.class);
            return PaqueteImp.editar(paquete); 
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("buscar/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> buscarPaquete(@PathParam("busqueda") String busqueda){
        if(busqueda !=null){
            return PaqueteImp.buscarPaquete(busqueda);
        }
        throw new BadRequestException();
    }
    
    @Path("eliminar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta darBaja(String json) {
        Gson gson = new Gson();
        try{
            Paquete paquete = gson.fromJson(json, Paquete.class);
            return PaqueteImp.quitarEnvio(paquete);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
    
    @Path("agregar-envio")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta agregarEnvio(String json) {
        Gson gson = new Gson();
        try{
            Paquete paquete = gson.fromJson(json, Paquete.class);
            return PaqueteImp.agregarEnvio(paquete);
        }catch (Exception e){
            throw new BadRequestException (e.getMessage());
        }
    }
}
