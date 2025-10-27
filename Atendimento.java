package Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Atendimento {
    private LocalTime inicio;
    private LocalTime fim;
    private Paciente paciente;
    private Medico medico;
    private Enfermeiro enfermeiro; // novo: enfermeiro que participou (pode ser null)

    public Atendimento(Paciente paciente, Medico medico) {
        this(paciente, medico, null);
    }

    public Atendimento(Paciente paciente, Medico medico, Enfermeiro enfermeiro) {
        this.paciente = paciente;
        this.medico = medico;
        this.enfermeiro = enfermeiro;
        this.inicio = LocalTime.now();
    }

    public void finalizar() {
        this.fim = LocalTime.now();
    }

    public LocalTime getInicio() { return inicio; }
    public LocalTime getFim() { return fim; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public Enfermeiro getEnfermeiro() { return enfermeiro; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        String chegada = (paciente.getHoraChegada() != null) ? paciente.getHoraChegada().format(fmt) : "--:--:--";
        String inicioStr = (inicio != null) ? inicio.format(fmt) : "--:--:--";
        String fimStr = (fim != null) ? fim.format(fmt) : "--:--:--";
        String enfermeiroStr = (enfermeiro != null) ? enfermeiro.getNome() : "—";

        return String.join(", ",
                "Paciente: " + paciente.getNome(),
                "Médico: " + medico.getNome(),
                "Enfermeiro: " + enfermeiroStr,
                "Chegada: " + chegada,
                "Início: " + inicioStr,
                "Fim: " + fimStr
        );
    }
}
