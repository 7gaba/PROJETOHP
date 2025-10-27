package Model;

public class Pessoa {
    protected String cpf;
    protected String nome;
    protected String dataNascimento;

    public Pessoa(String cpf, String nome, String dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append("cpf='").append(cpf).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", dataNascimento='").append(dataNascimento).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
