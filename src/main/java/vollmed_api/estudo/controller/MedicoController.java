package vollmed_api.estudo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vollmed_api.estudo.dto.DadosAtualizacaoMedico;
import vollmed_api.estudo.dto.DadosDetalhamentoMedico;
import vollmed_api.estudo.dto.DadosListagemMedico;
import vollmed_api.estudo.dto.dadosCadastroMedico;
import vollmed_api.estudo.entities.medico.Medico;
import vollmed_api.estudo.entities.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid dadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
       medicoRepository.save(medico);

//     Cria a url do objeto que foi criado caso o ID exista, para pegar o location
       var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
//     Retorna a URL cadastrada e o corpo que é um DTO de médico
       return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); // code 201
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page); // code 200
    }

    @GetMapping("/{id}")
    public ResponseEntity listarDetalhado(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // code 200
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // 200 code
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build(); // 204 code
    }

}
