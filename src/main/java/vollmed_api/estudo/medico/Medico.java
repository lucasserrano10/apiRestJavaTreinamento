package vollmed_api.estudo.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vollmed_api.estudo.dto.Especialidade;
import vollmed_api.estudo.dto.dadosCadastroMedico;
import vollmed_api.estudo.endereco.Endereco;

@Table(name="medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
//  Embedded attribute é quando não criamos uma tabela e fazemos relacionamento entre elas, apenas fazemos esse esquema para dizer que estão juntos
    @Embedded
    private Endereco endereco;

    public Medico(dadosCadastroMedico dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = Especialidade.DERMATOLOGIA;
        this.endereco = new Endereco(dados.endereco());
    }
}
