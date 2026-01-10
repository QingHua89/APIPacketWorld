package dto;

import pojo.Sucursal;

public class RSAutenticacionSucursal {
    
    private boolean error;
    private String mensaje;
    private Sucursal sucursal;

    public RSAutenticacionSucursal() {
    }

    public RSAutenticacionSucursal(boolean error, String mensaje, Sucursal sucursal) {
        this.error = error;
        this.mensaje = mensaje;
        this.sucursal = sucursal;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
}
