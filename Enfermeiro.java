package Model;

public class Enfermeiro extends Pessoa {
    private String coren;

    public Enfermeiro(String cpf, String nome, String dataNascimento, String coren) {
        super(cpf, nome, dataNascimento);
        this.coren = coren;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enfermeiro{");
        sb.append("coren='").append(coren).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
