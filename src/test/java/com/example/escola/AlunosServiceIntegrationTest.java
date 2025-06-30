package com.example.escola;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.escola.model.AlunosModel;
import com.example.escola.service.AlunosService;

@SpringBootTest // carrega o contexto Spring real, incluindo banco H2 se configurado
class AlunosServiceIntegrationTest {

    @Autowired
    private AlunosService alunosService;
    /*
     * Aqui ele injeta de verdade o AlunosService,
     * que por sua vez injeta o AlunosRepository real.
     * Assim, salva no banco de teste H2 em memória.
     */

     /*
      * Nome: Teste de Cadastro de aluno
      * Oojetivo: validar se o programa realmente esta cadastrando de forma automatizada.
      */
    @Test
    void testSalvarEBuscarAluno() {
        System.out.println("[TESTE] Iniciando teste de salvar e buscar aluno...");
        // cria aluno
        AlunosModel aluno = new AlunosModel();
        aluno.setNome("Wellerson");
        aluno.setEmail("wellersonfc@gmail.com");

        System.out.println("[TESTE] Tentando salvar o aluno: " + aluno.getNome());

        // salva usando o service real
        AlunosModel salvo = alunosService.salvar(aluno);

        // faz asserções
        assertNotNull(salvo.getId(), "[ASSERT] O ID não deveria ser nulo");
        assertEquals("Wellerson", salvo.getNome(), "[ASSERT] O nome deveria ser Wellerson");

        // busca do banco real (H2)
        var alunoBuscado = alunosService.buscarPorId(salvo.getId());
        System.out.println("[TESTE] Buscando aluno com ID: " + salvo.getId());


        assertTrue(alunoBuscado.isPresent(), "[ASSERT] O aluno buscado deve existir");
        assertEquals("Wellerson", alunoBuscado.get().getNome(), "[ASSERT] O nome do aluno buscado deveria ser Wellerson");

        System.out.println("[TESTE] Teste finalizado com sucesso!");
    }
}
