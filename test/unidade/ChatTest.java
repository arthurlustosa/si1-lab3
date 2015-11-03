package unidade;

import models.Chat;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatTest {

    private String pergunta1;
    private String resposta1;
    private Chat conversa;

    @Before
    public void setUp() {
        pergunta1 = "Pp hoje?";
        resposta1 = "Bora!";
    }

    @Test
    public void deveCriarConversaSemErros() {
        try {
            new Chat(pergunta1);
        } catch (Exception e) {
            fail("Não deveria lançar exceção.");
        }
    }

    @Test
    public void naoDeveCriarConversaSemPergunta() {
        pergunta1 = "";

        try {
            new Chat(pergunta1);
            fail("Não deveria criar conversa sem pergunta.");
        } catch (Exception e) {
        }

        try {
            new Chat(null);
            fail("Não deveria criar conversa sem pergunta.");
        } catch (Exception e) {
        }
    }

    @Test
    public void criaRespostaSemErros() {
        try {
            conversa = new Chat(pergunta1);
        } catch (Exception e) {
            fail("Não deveria lançar exceção.");
        }

        try {
            conversa.setResposta(resposta1);
        } catch (Exception e) {
            fail("Não deveria lançar exceção.");
        }
    }
}