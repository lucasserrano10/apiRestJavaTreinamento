package vollmed_api.estudo.entities.medico.consulta.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) throws ValidacaoException {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora,dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
