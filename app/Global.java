
import models.Anuncio;
        import play.*;

        import models.dao.GenericDAO;
        import play.db.jpa.JPA;


public class Global extends GlobalSettings {

    private static GenericDAO DAO = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("Aplicação inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                for (int i = 1; i < 26; i++) {
                    Anuncio anuncio = new Anuncio();
                    anuncio.setTitulo("Teste " + i);
                    anuncio.setDescricao("Descrição de teste " + i);
                    anuncio.setCidade("Cidade de teste " + i);
                    anuncio.setBairro("Bairro de teste " + i);
                    anuncio.setInstrumentos("Instrumentos " + i);
                    anuncio.setEstilos("Estilos que gosto " + i);
                    anuncio.setEstilosBanidos("Estilos que não gosto " + i);
                    anuncio.setContatos("Contatos " + i);

                    if (i % 2 == 0) {
                        anuncio.setInteresses("Banda");
                    } else {
                        anuncio.setInteresses("Ocasionalmente");
                    }

                    anuncio.setCodigo("teste" + i);

                    DAO.persist(anuncio);
                    DAO.flush();
                }
            }
        });
    }
}