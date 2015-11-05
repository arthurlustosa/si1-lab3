package base;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import models.Anuncio;


import org.junit.Test;

import play.db.jpa.JPA;
import play.test.FakeApplication;
import play.test.WithBrowser;

/**
 *
 * @author Vinicius
 *
 */
public class TestePersistencia extends WithBrowser {

    @Override
    public FakeApplication provideFakeApplication() {
        return fakeApplication(inMemoryDatabase());
    }

    @Test
    public void test() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                Anuncio anuncio = new Anuncio("Teste Anuncio1", "Testando persistencia", "Campina Grande", "Centro", "flauta violao", "indie folk", "funk teckno", "facebook.com/arthur.lustosa", "Formar banda", "senha123");

                GenericRepositoryImpl<Anuncio> anuncioRepositorio = AnuncioRepositorio
                        .getInstance();

                anuncioRepositorio.persist(anuncio);
                List<Anuncio> anuncioLista;

                anuncioLista = anuncioRepositorio.findAll();

                assertThat(anuncioLista.size()).isEqualTo(1);
                assertThat(anuncioLista.get(0).getTitulo()).isEqualTo("Teste Anuncio1");
                assertThat(anuncioLista.get(0).getDescricao()).isEqualTo("Testando persistencia");
                assertThat(anuncioLista.get(0).getCidade()).isEqualTo("Campina Grande");
                assertThat(anuncioLista.get(0).getCidade()).isEqualTo("Centro");
                assertThat(anuncioLista.get(0).getInstrumentos()).isEqualTo("flauta violao");
                assertThat(anuncioLista.get(0).getEstilos()).isEqualTo("indie folk");
                assertThat(anuncioLista.get(0).getEstilosBanidos()).isEqualTo("funk teckno");
                assertThat(anuncioLista.get(0).getContatos()).isEqualTo("facebook.com/arthur.lustosa");
                assertThat(anuncioLista.get(0).getInteresses()).isEqualTo("Formar banda");


            }
        });

    }

}