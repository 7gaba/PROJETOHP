package Model;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalTime;

public class Fila {
    private PriorityQueue<Paciente> listaPacientes;

    public Fila() {
        this.listaPacientes = new PriorityQueue<>(new Comparator<Paciente>() {
            private int rank(String classificacao) {
                if (classificacao == null) return 4;
                switch (classificacao.toLowerCase()) {
                    case "vermelho": return 1;
                    case "amarelo": return 2;
                    case "verde": return 3;
                    case "azul": return 4;
                    default: return 4;
                }
            }

            @Override
            public int compare(Paciente p1, Paciente p2) {
                int r1 = rank(p1.getClassificacao());
                int r2 = rank(p2.getClassificacao());
                if (r1 != r2) return Integer.compare(r1, r2);
                LocalTime h1 = p1.getHoraChegada();
                LocalTime h2 = p2.getHoraChegada();
                if (h1 != null && h2 != null) {
                    int cmp = h1.compareTo(h2);
                    if (cmp != 0) return cmp;
                }
                return p1.getNome().compareToIgnoreCase(p2.getNome());
            }
        });
    }

    public void inserirPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    public Paciente removerPaciente() {
        return listaPacientes.poll();
    }

    public boolean isEmpty() {
        return listaPacientes.isEmpty();
    }

    public List<Paciente> listarPendentesOrdenados() {
        List<Paciente> copia = new ArrayList<>(listaPacientes);
        copia.sort(listaPacientes.comparator());
        return copia;
    }

    public void mostrarFila() {
        System.out.println("=== FILA DE PACIENTES (PRIORIDADE) ===");
        if (listaPacientes.isEmpty()) {
            System.out.println("Nenhum paciente na fila.");
            return;
        }
        for (Paciente p : listarPendentesOrdenados()) {
            System.out.println(p);
        }
    }

    public int tamanho() {
        return listaPacientes.size();
    }

    public Queue<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    @Override
    public String toString() {
        return "Fila com " + listaPacientes.size() + " pacientes.";
    }
}
