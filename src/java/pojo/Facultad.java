package pojo;

public class Facultad {
    
    private int idFacultad;
    private String facultad;

    public Facultad() {
    }

    public Facultad(int idFacultad, String facultad) {
        this.idFacultad = idFacultad;
        this.facultad = facultad;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    
}
