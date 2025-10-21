package vollmed_api.estudo.dto;

import vollmed_api.estudo.entities.medico.paciente.Paciente;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String cpf)
{
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNome(),paciente.getEmail(), paciente.getCpf());
    }
}
