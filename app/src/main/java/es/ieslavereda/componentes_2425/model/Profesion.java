package es.ieslavereda.componentes_2425.model;

public enum Profesion {
    FRONT("Front developer"), BACK("Back developer"),
    FULL("FullStack developer"), QA("Quality assurance tester"),
    SCRUM_MASTER("Scrum master"), DEVOPS("DevOps");

    private String profesion;
    Profesion(String profesion){
        this.profesion=profesion;
    }
    public String getProfesion() {
        return profesion;
    }
}
