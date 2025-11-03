package vollmed_api.estudo.entities.medico.consulta.validacoes;

import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dados) throws ValidacaoException;
}
