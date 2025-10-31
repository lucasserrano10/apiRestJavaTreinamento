package vollmed_api.estudo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vollmed_api.estudo.entities.medico.consulta.Consulta;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivoCancelamento

) {
}
