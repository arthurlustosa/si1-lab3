package funcional;

import controllers.Application;
import models.Anuncio;
import models.Chat;
import models.dao.GenericDAO;
import org.junit.*;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.Helpers;
import scala.Option;


import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class ApplicationTest {
    private static FakeApplication app;
    public EntityManager em;
    private GenericDAO DAO = new GenericDAO();

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication(new Global());
        Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void setUp() {
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @Test
    public void deveCarregarIndexOk() {
        Result result = Application.index();
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void deveCarregarAnunciosOk() {
        Result result = callAction(controllers.routes.ref.Application.anuncios(), new FakeRequest());
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void deveCriarAnuncio() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("titulo", "Testando");
        requestMap.put("descricao", "Só uma descrição de teste...");
        requestMap.put("cidade", "Mãe D'água");
        requestMap.put("bairro", "Centro");
        requestMap.put("instrumentos", "Violão e Gaita");
        requestMap.put("estilos", "Forró pé de serrá");
        requestMap.put("estilosBanidos", "Pagode");
        requestMap.put("contatos", "Número do celular");
        requestMap.put("codigo", "testando1");
        requestMap.put("interesse", "Banda");

        FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(requestMap);
        Result resultPost = callAction(controllers.routes.ref.Application.novoAnuncio(), fakeRequest);
        assertThat(status(resultPost)).isEqualTo(OK);

        Result resultGet = callAction(controllers.routes.ref.Application.anuncios(), new FakeRequest());
        assertThat(status(resultGet)).isEqualTo(OK);
        assertThat(contentType(resultGet)).isEqualTo("text/html");
        assertThat(contentAsString(resultGet)).contains("Testando");
    }

    @Test
    public void deveFinalizarAnuncio() {
        List<Anuncio> anuncios = DAO.findAllByClass(Anuncio.class);
        Anuncio anuncioTest = anuncios.get(0);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("finalizar", "teste1");
        requestMap.put("encontrouParceiros", "Sim");

        FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(requestMap);
        Result resultPost = callAction(controllers.routes.ref.Application.finalizaAnuncio("teste1", anuncioTest.getId()), fakeRequest);
        assertThat(status(resultPost)).isEqualTo(OK);
        assertThat(contentAsString(resultPost)).contains("<h4>@finalizados Anúncios finalizados</h4>");
        assertThat(contentAsString(resultPost)).doesNotContain("<h4 class='anuncioTitulo'>Teste 1</h4>");
    }

    @Test
    public void deveFazerPerguntaEResposta() {
        List<Anuncio> anuncios = DAO.findAllByClass(Anuncio.class);
        Anuncio anuncioTest = anuncios.get(0);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("pergunta", "Pergunta de teste");

        FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(requestMap);
        Result resultPost = callAction(controllers.routes.ref.Application.fazerPergunta(anuncioTest.getId()), fakeRequest);
        assertThat(status(resultPost)).isEqualTo(OK);
        assertThat(contentAsString(resultPost)).contains("Pergunta de teste");

        Conversa conversa = anuncioTest.getConversas().get(0);

        Map<String, String> requestMap2 = new HashMap<>();
        requestMap2.put("resposta", "Resposta de teste");
        requestMap2.put("codigo", "teste1");

        FakeRequest fakeRequest2 = new FakeRequest().withFormUrlEncodedBody(requestMap2);
        Result resultPost2 = callAction(controllers.routes.ref.Application.responderPergunta(conversa.getId(), anuncioTest.getId()), fakeRequest2);
        assertThat(status(resultPost2)).isEqualTo(OK);
        assertThat(contentAsString(resultPost2)).contains("Resposta de teste");
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }
}