package vollmed_api.estudo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vollmed_api.estudo.dto.DadosAgendamentoConsulta;
import vollmed_api.estudo.dto.DadosCancelamentoConsulta;
import vollmed_api.estudo.dto.DadosDetalhamentoConsulta;
import vollmed_api.estudo.entities.medico.consulta.AgendaDeConsultas;
import vollmed_api.estudo.entities.medico.consulta.ValidacaoException;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) throws ValidacaoException {
        System.out.println("Data recebida: " + dados.data());
        agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null,null,null,null));
    }

    @DeleteMapping
    public ResponseEntity cancelandoConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados) throws ValidacaoException {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
