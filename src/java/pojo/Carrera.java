package pojo;

public class Carrera {
    private int idCarrera;
    private String carrera;
    private int idFacultad;

    public Carrera() {
    }

    public Carrera(int idCarrera, String carrera, int idFacultad) {
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.idFacultad = idFacultad;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }
}
