package vollmed_api.estudo.entities.medico.consulta.validacoes.cancelamento;

import org.springframework.stereotype.Component;
import vollmed_api.estudo.dto.DadosCancelamentoConsulta;
import vollmed_api.estudo.dto.MotivoCancelamento;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;
import vollmed_api.estudo.entities.medico.consulta.validacoes.ValidadorCancelamentoConsulta;

@Component
public class ValidadorMotivoCancelamento implements ValidadorCancelamentoConsulta {


    @Override
    public void validarCancelamento(DadosCancelamentoConsulta dados) throws ValidacaoException {
        var motivoCancelamento = dados.motivoCancelamento();
        if (motivoCancelamento == null){
            throw new ValidacaoException("É obrigatório informar o motivo de cancelamento");
        }

        try {
            MotivoCancelamento.valueOf(motivoCancelamento.name());
        } catch (IllegalArgumentException e) {
            throw new ValidacaoException("Motivo de cancelamento inválido");
        }

    }

}
