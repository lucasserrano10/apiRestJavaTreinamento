package vollmed_api.estudo.entities.medico.consulta.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;
import vollmed_api.estudo.entities.medico.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) throws ValidacaoException {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }

}
