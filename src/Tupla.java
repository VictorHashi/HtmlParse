public class Tupla {

    private String professor;
    private int disciplinas;

    public Tupla(String string){
        this.disciplinas = 0;
        this.professor = string;

    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(int disciplinas) {
        this.disciplinas = disciplinas;
    }
}
