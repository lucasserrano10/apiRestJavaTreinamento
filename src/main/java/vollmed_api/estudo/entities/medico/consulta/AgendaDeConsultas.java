package vollmed_api.estudo.entities.medico.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.dto.DadosCancelamentoConsulta;
import vollmed_api.estudo.dto.MotivoCancelamento;
import vollmed_api.estudo.entities.medico.Medico;
import vollmed_api.estudo.entities.medico.MedicoRepository;
import vollmed_api.estudo.entities.medico.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) throws ValidacaoException {
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do Paciente informado não existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do Médico informado não existe");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(medico,paciente,dados.data());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) throws ValidacaoException {
       if(dados.idMedico() != null){
           return medicoRepository.getReferenceById(dados.idMedico());
       }

       if(dados.especialidade() == null){
           throw new ValidacaoException("Especialidade é Obrigatória quando médico não é escolhido");
       }

       return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());

    }


    public void cancelar(DadosCancelamentoConsulta dados) throws ValidacaoException {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }
}
