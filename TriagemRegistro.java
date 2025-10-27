package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TriagemRegistro {
    private Enfermeiro enfermeiro;
    private Paciente paciente;
    private List<String> respostas;
    private int score;
    private String classificacao;
    private LocalDateTime hora;

    public TriagemRegistro(Enfermeiro enfermeiro, Paciente paciente, List<String> respostas, int score, String classificacao) {
        this.enfermeiro = enfermeiro;
        this.paciente = paciente;
        this.respostas = respostas;
        this.score = score;
        this.classificacao = classificacao;
        this.hora = LocalDateTime.now();
    }

    public Enfermeiro getEnfermeiro() { return enfermeiro; }
    public Paciente getPaciente() { return paciente; }
    public int getScore() { return score; }
    public String getClassificacao() { return classificacao; }
    public LocalDateTime getHora() { return hora; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String h = hora.format(fmt);
        return String.join(", ",
                "Triagem: paciente: " + paciente.getNome(),
                "classificação: " + classificacao,
                "score: " + score,
                "enfermeiro: " + (enfermeiro != null ? enfermeiro.getNome() : "—"),
                "hora: " + h
        );
    }
}
