package vollmed_api.estudo.entities.medico.consulta.validacoes;

import vollmed_api.estudo.dto.DadosCancelamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;

public interface ValidadorCancelamentoConsulta {
    void validarCancelamento(DadosCancelamentoConsulta dados) throws ValidacaoException;
}
