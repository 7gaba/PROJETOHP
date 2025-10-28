package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        if (cpf.length() == 11) {
            this.cpf = cpf;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        try {
            LocalDate data = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (data.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Data de nascimento não pode ser no futuro.");
            }
            this.dataNascimento = dataNascimento;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de nascimento inválida. Use o formato yyyy-MM-dd.");
        }
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
