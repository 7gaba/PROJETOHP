package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Triagem {

    public TriagemRegistro realizarTriagem(Enfermeiro enfermeiro) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do paciente: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Data de nascimento: ");
        String dataNascimento = sc.nextLine();

        System.out.println("Responda S ou N:");

        int score = 0;
        List<String> respostas = new ArrayList<>();

        System.out.print("1) Dificuldade para respirar? (S/N): ");
        String r1 = sc.nextLine(); respostas.add(r1);
        if (respostaSim(r1)) score += 5;

        System.out.print("2) Dor intensa ou hemorragia? (S/N): ");
        String r2 = sc.nextLine(); respostas.add(r2);
        if (respostaSim(r2)) score += 4;

        System.out.print("3) Febre alta ou vômitos persistentes? (S/N): ");
        String r3 = sc.nextLine(); respostas.add(r3);
        if (respostaSim(r3)) score += 2;

        System.out.print("4) Pode aguardar algumas horas sem risco? (S/N): ");
        String r4 = sc.nextLine(); respostas.add(r4);
        if (!respostaSim(r4)) score += 1;

        String classificacao = mapScoreParaClassificacao(score);

        System.out.print("Descreva os sintomas: ");
        String sintomas = sc.nextLine();

        Paciente paciente = new Paciente(cpf, nome, dataNascimento, sintomas, classificacao);

        TriagemRegistro registro = new TriagemRegistro(enfermeiro, paciente, respostas, score, classificacao);
        return registro;
    }

    private boolean respostaSim(String s) {
        if (s == null) return false;
        s = s.trim().toLowerCase();
        return s.equals("s") || s.equals("sim") || s.equals("y") || s.equals("yes");
    }

    private String mapScoreParaClassificacao(int score) {
        if (score >= 6) return "Vermelho";
        if (score >= 4) return "Amarelo";
        if (score >= 2) return "Verde";
        return "Azul";
    }
}
