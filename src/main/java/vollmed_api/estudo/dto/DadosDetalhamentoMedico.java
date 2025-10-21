package vollmed_api.estudo.dto;

import vollmed_api.estudo.entities.medico.Medico;
import vollmed_api.estudo.entities.medico.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm ,String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }

}
