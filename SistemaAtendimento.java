package Service;

import Model.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class SistemaAtendimento {
    private Fila fila;
    private List<Medico> medicos;
    private List<Enfermeiro> enfermeiros;
    private List<Atendimento> historico;
    private List<TriagemRegistro> triagens;

    public SistemaAtendimento() {
        this.fila = new Fila();
        this.medicos = new ArrayList<>();
        this.enfermeiros = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.triagens = new ArrayList<>();
    }

    public Fila getFila() { return fila; }
    public List<Medico> getMedicos() { return medicos; }
    public List<Enfermeiro> getEnfermeiros() { return enfermeiros; }
    public List<Atendimento> getHistorico() { return historico; }
    public List<TriagemRegistro> getTriagens() { return triagens; }

    public void adicionarMedico(Medico medico) {
        if (medico != null) medicos.add(medico);
    }

    public void adicionarEnfermeiro(Enfermeiro enfermeiro) {
        if (enfermeiro != null) enfermeiros.add(enfermeiro);
    }

    public void adicionarPaciente(Paciente paciente) {
        if (paciente != null) {
            fila.inserirPaciente(paciente);
            System.out.println("Paciente inserido na fila: " + paciente.getNome() + ", classificação: " + paciente.getClassificacao());
        }
    }

    public void registrarTriagem(TriagemRegistro registro) {
        if (registro == null) return;
        triagens.add(registro);
        adicionarPaciente(registro.getPaciente());
        System.out.println("Triagem registrada: " + registro);
    }

    public void mostrarFila() {
        fila.mostrarFila();
    }

    public void mostrarPacientesPendentes() {
        System.out.println("\n=== PACIENTES PENDENTES (RESUMO ORDENADO) ===");
        List<Paciente> pendentes = fila.listarPendentesOrdenados();
        if (pendentes.isEmpty()) {
            System.out.println("Nenhum paciente pendente.");
            return;
        }
        int i = 1;
        for (Paciente p : pendentes) {
            System.out.println(i + " - " + p);
            i++;
        }
    }

    public void mostrarMedicos() {
        System.out.println("\n=== MÉDICOS CADASTRADOS ===");
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (Medico m : medicos) System.out.println(m);
    }

    public void mostrarEnfermeiros() {
        System.out.println("\n=== ENFERMEIROS CADASTRADOS ===");
        if (enfermeiros.isEmpty()) {
            System.out.println("Nenhum enfermeiro cadastrado.");
            return;
        }
        for (Enfermeiro e : enfermeiros) System.out.println(e);
    }

    public void simularAtendimento() {
        simularAtendimento(null);
    }

    public void simularAtendimento(Enfermeiro enfermeiroParticipante) {
        if (fila.isEmpty()) {
            System.out.println("Nenhum paciente na fila.");
            return;
        }
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado!");
            return;
        }

        Paciente paciente = fila.removerPaciente();
        if (paciente == null) {
            System.out.println("Erro ao remover paciente da fila.");
            return;
        }

        Medico medico = medicos.get(new Random().nextInt(medicos.size()));
        Atendimento atendimento = new Atendimento(paciente, medico, enfermeiroParticipante);

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        atendimento.finalizar();
        paciente.adicionarAtendimento(atendimento);
        medico.registrarAtendimento(atendimento);
        historico.add(atendimento);

        System.out.println("Atendimento finalizado: " + atendimento);
    }

    public void mostrarHistoricoGeral() {
        System.out.println("\n=== HISTÓRICO GERAL DE ATENDIMENTOS ===");
        if (historico.isEmpty()) {
            System.out.println("Nenhum atendimento realizado ainda.");
            return;
        }
        for (Atendimento a : historico) System.out.println(a);
    }

    public void mostrarHistoricoPaciente(String nomePaciente) {
        System.out.println("\n=== HISTÓRICO DO PACIENTE: " + nomePaciente.toUpperCase() + " ===");
        boolean encontrado = false;
        for (Atendimento a : historico) {
            if (a.getPaciente().getNome().equalsIgnoreCase(nomePaciente)) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("Nenhum atendimento encontrado para esse paciente.");
    }

    public void mostrarHistoricoMedico(String nomeMedico) {
        System.out.println("\n=== HISTÓRICO DO MÉDICO: " + nomeMedico.toUpperCase() + " ===");
        boolean encontrado = false;
        for (Atendimento a : historico) {
            if (a.getMedico().getNome().equalsIgnoreCase(nomeMedico)) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("Nenhum atendimento encontrado para esse médico.");
    }

    public void mostrarHistoricoTriagens() {
        System.out.println("\n=== HISTÓRICO DE TRIAGENS ===");
        if (triagens.isEmpty()) {
            System.out.println("Nenhuma triagem registrada ainda.");
            return;
        }
        for (TriagemRegistro t : triagens) System.out.println(t);
    }

    @Override
    public String toString() {
        return "SistemaAtendimento, médicos: " + medicos.size() + ", enfermeiros: " + enfermeiros.size() + ", pacientes na fila: " + fila.getListaPacientes().size() + ", atendimentos realizados: " + historico.size();
    }
}
