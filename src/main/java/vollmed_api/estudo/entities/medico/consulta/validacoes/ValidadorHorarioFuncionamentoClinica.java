package vollmed_api.estudo.entities.medico.consulta.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {


    public void validar(DadosAgendamentoConsulta dados) throws ValidacaoException {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaClinica || depoisDoEncerramentoClinica){
            throw new ValidacaoException("Consulta Fora do Horário de Funcionamento da Clínica");
        }
    }

}
