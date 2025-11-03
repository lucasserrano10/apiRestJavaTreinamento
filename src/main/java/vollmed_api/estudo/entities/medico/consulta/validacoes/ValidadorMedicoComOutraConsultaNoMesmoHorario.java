package vollmed_api.estudo.entities.medico.consulta.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.ConsultaRepository;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) throws ValidacaoException {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
