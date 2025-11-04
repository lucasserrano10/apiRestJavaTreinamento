package vollmed_api.estudo.entities.medico.consulta.validacoes.cancelamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vollmed_api.estudo.dto.DadosCancelamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ConsultaRepository;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;
import vollmed_api.estudo.entities.medico.consulta.validacoes.ValidadorCancelamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioCancelamento implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validarCancelamento(DadosCancelamentoConsulta dados) throws ValidacaoException {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}


