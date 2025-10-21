package vollmed_api.estudo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        String nome,
        String telefone,
        DadosEndereco endereco)
{

}
