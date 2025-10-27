import Model.*;
import Service.SistemaAtendimento;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAtendimento sistema = new SistemaAtendimento();
        Triagem triagem = new Triagem();

        sistema.adicionarMedico(new Medico("11111111111", "Kauan", "1980-05-01", "CRM1234", "Urologista"));
        sistema.adicionarMedico(new Medico("22222222222", "Carlos", "1975-02-10", "CRM5678", "Pediatra"));

        sistema.adicionarEnfermeiro(new Enfermeiro("44444444444", "Enrique", "1990-04-04", "COREN123"));
        sistema.adicionarEnfermeiro(new Enfermeiro("55555555555", "Ricardo", "1986-08-08", "COREN456"));

        List<Paciente> iniciais = Arrays.asList(
                new Paciente("10000000001","Paciente1","1990-01-01","Dor leve","Verde"),
                new Paciente("10000000002","Paciente2","1991-02-02","Febre alta","Amarelo"),
                new Paciente("10000000003","Paciente3","1988-03-03","Fratura exposta","Vermelho"),
                new Paciente("10000000004","Paciente4","2000-04-04","Tontura","Verde")
        );

        for (Paciente p : iniciais) sistema.adicionarPaciente(p);

        int opc;
        do {
            System.out.println("\n=== MENU ATENDIMENTO MÉDICO ===");
            System.out.println("1 - Adicionar paciente via triagem (por enfermeiro)");
            System.out.println("2 - Mostrar fila de pacientes (prioridade)");
            System.out.println("3 - Simular atendimento");
            System.out.println("4 - Mostrar histórico geral");
            System.out.println("5 - Mostrar histórico de um paciente");
            System.out.println("6 - Mostrar histórico de um médico");
            System.out.println("7 - Mostrar médicos");
            System.out.println("8 - Mostrar enfermeiros");
            System.out.println("9 - Mostrar triagens realizadas");
            System.out.println("10 - Mostrar pacientes pendentes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    sistema.mostrarEnfermeiros();
                    System.out.print("Digite o nome do enfermeiro que fará a triagem: ");
                    String nomeEnf = sc.nextLine();
                    Enfermeiro enfSel = null;
                    for (Enfermeiro e : sistema.getEnfermeiros())
                        if (e.getNome().equalsIgnoreCase(nomeEnf)) enfSel = e;
                    if (enfSel == null) {
                        System.out.println("Enfermeiro não encontrado.");
                        break;
                    }
                    TriagemRegistro reg = triagem.realizarTriagem(enfSel);
                    sistema.registrarTriagem(reg);
                    break;

                case 2:
                    sistema.mostrarFila();
                    break;

                case 3:
                    sistema.simularAtendimento();
                    break;

                case 4:
                    sistema.mostrarHistoricoGeral();
                    break;

                case 5:
                    System.out.print("Nome do paciente: ");
                    String nomePac = sc.nextLine();
                    sistema.mostrarHistoricoPaciente(nomePac);
                    break;

                case 6:
                    System.out.print("Nome do médico: ");
                    String nomeMed = sc.nextLine();
                    sistema.mostrarHistoricoMedico(nomeMed);
                    break;

                case 7:
                    sistema.mostrarMedicos();
                    break;

                case 8:
                    sistema.mostrarEnfermeiros();
                    break;

                case 9:
                    sistema.mostrarHistoricoTriagens();
                    break;

                case 10:
                    sistema.mostrarPacientesPendentes();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opc != 0);

        sc.close();
    }
}
