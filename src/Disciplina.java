public class Disciplina {
    private int id;
    private String nome;
    private String professor;
    private double nota;
    private int faltas;

    public Disciplina(int id, String nome, String professor, double nota, int faltas){
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.nota = nota;
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Disciplina{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", professor='").append(professor).append('\'');
        sb.append(", nota=").append(nota);
        sb.append(", faltas=").append(faltas);
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
