package Model;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {
    private String crm;
    private String especialidade;
    private List<Atendimento> historicoAtendimentos;

    public Medico(String cpf, String nome, String dataNascimento, String crm, String especialidade) {
        super(cpf, nome, dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
        this.historicoAtendimentos = new ArrayList<>();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Atendimento> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public void registrarAtendimento(Atendimento atendimento) {
        historicoAtendimentos.add(atendimento);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medico{");
        sb.append("crm='").append(crm).append('\'');
        sb.append(", especialidade='").append(especialidade).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
