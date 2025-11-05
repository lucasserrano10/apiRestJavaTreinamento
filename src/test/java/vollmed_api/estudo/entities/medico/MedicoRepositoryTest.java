package vollmed_api.estudo.entities.medico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import vollmed_api.estudo.dto.DadosEndereco;
import vollmed_api.estudo.dto.DadosPaciente;
import vollmed_api.estudo.dto.Especialidade;
import vollmed_api.estudo.dto.dadosCadastroMedico;
import vollmed_api.estudo.entities.medico.consulta.Consulta;
import vollmed_api.estudo.entities.medico.paciente.Paciente;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Dr Matheus", "medico@vollmed.api", "987652", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@gmail.com", "00000000000");
        cadastrarConsulta(medico,paciente,proximaSegundaAsDez);

       var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
       assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(medico,paciente,data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade){
        var medico = new Medico(dadosMedico(nome,email,crm,especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf){
        var paciente = new Paciente((dadosPaciente(nome,email,cpf)));
        em.persist(paciente);
        return paciente;
    }

    private dadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade){
        return new dadosCadastroMedico(
                nome,
                email,
                "6199999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco(){
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                 null,
                null
        );
    }
}