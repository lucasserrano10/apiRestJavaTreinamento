package vollmed_api.estudo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) throws ValidacaoException {
        var dto = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity cancelandoConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados) throws ValidacaoException {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
