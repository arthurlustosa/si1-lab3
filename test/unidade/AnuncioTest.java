package unidade;

import models.Anuncio;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
public class AnuncioTest {

    private String titulo1;
    private String descricao1;
    private String cidade1;
    private String bairro1;
    private String instrumentos1;
    private String estilos1;
    private String estilosBanidos1;
    private String contatos1;
    private String codigo1;
    private String interesse1;

    private Anuncio anuncio1;

    @Before
    public void setUp() throws Exception {
        titulo1 = "Título1";
        descricao1 = "Descrição de teste";
        cidade1 = "Campina Grande";
        bairro1 = "Bodocongó";
        instrumentos1 = "Violão, Guitarra";
        estilos1 = "Rock, Jazz, Blues";
        estilosBanidos1 = "Pagode, Funk";
        contatos1 = "teste@gmail.com";
        codigo1 = "codigoteste";
        interesse1 = "Formar Banda";

        anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                interesse1);
    }

    @Test
    public void deveCriarAnuncioSemErros() {
        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
        } catch (Exception e) {
            fail("Não deveria lançar exceção.");
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemTitulo() {
        titulo1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(null, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemDescricao() {
        descricao1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, null, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemCidade() {
        cidade1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, descricao1, null, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemBairro() {
        bairro1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, descricao1, cidade1, null, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemInstrumentos() {
        instrumentos1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, null, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void deveCriarAnuncioSemEstilos() {
        estilos1 = "";
        estilosBanidos1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
        } catch (Exception e) {
            fail("Não deveria lançar exceção.");
        }

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, null, null, contatos1, codigo1,
                    interesse1);
        } catch (Exception e) {
            fail("Não deveria lançar exceção.");
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemContatos() {
        contatos1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, null, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemCodigo() {
        codigo1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, null,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarAnuncioSemInteresse() {
        interesse1 = "";

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    interesse1);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }

        try {
            new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1, estilos1, estilosBanidos1, contatos1, codigo1,
                    null);
            fail("Deveria lançar exceção.");
        } catch (Exception e) {
        }
    }

    @Test
    public void naoDeveCriarRespostaComCodigoErrado() {
        anuncio1.fazerPergunta("Pergunta de teste.");
        assertEquals(anuncio1.getConversas().size(), 1);

        Long idConversa = anuncio1.getConversas().get(0).getId();

        try {
            anuncio1.responderPergunta(idConversa, "Resposta de teste", "codigo errado");
            fail("Deveria lança exceção.");
        } catch (Exception e) {
        }
    }
}