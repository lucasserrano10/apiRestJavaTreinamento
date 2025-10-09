package vollmed_api.estudo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vollmed_api.estudo.dto.dadosCadastroMedico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody dadosCadastroMedico dadosCadastroMedico){
        System.out.println(dadosCadastroMedico);
    }

}
