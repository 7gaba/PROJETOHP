package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Paciente extends Pessoa {
    private String classificacao;
    private String sintomas;
    private LocalTime horaChegada;
    private List<Atendimento> historicoAtendimentos;

    public Paciente(String cpf, String nome, String dataNascimento, String sintomas, String classificacao) {
        super(cpf, nome, dataNascimento);
        this.sintomas = sintomas;
        this.classificacao = classificacao;
        this.horaChegada = LocalTime.now();
        this.historicoAtendimentos = new ArrayList<>();
    }

    public String getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(String classificacao) { this.classificacao = classificacao; }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalTime horaChegada) {
        this.horaChegada = horaChegada;
    }

    public List<Atendimento> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        historicoAtendimentos.add(atendimento);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        String chegada = (horaChegada != null) ? horaChegada.format(fmt) : "--:--:--";
        return String.join(", ",
                Arrays.asList(
                        "Paciente: " + getNome(),
                        "Classificação: " + classificacao,
                        "Sintomas: " + sintomas,
                        "Chegada: " + chegada
                )
        );
    }
}
