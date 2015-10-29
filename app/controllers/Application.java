package controllers;

        import models.Anuncio;
        import models.dao.GenericDAO;
        import play.data.Form;
        import play.db.jpa.Transactional;
        import play.mvc.Controller;
        import play.mvc.Result;
        import views.html.index;

        import java.util.Collections;
        import java.util.List;

public class Application extends Controller {
    private static final GenericDAO DAO = new GenericDAO();
    private static Form<Anuncio> form = Form.form(Anuncio.class);
    private static Form<String> formString = Form.form(String.class);
    private static int anunciosFinalizados = 0;

    @Transactional
    public Result index() {

        return anuncios();
    }

    @Transactional
    public Result anuncios() {
        List<Anuncio> resultado = DAO.findAllByClass(Anuncio.class);
        Collections.sort(resultado);

        return ok(index.render(resultado, false, anunciosFinalizados));
    }

    @Transactional
    public Result novoAnuncio() {
        Form<Anuncio> formPreenchido = form.bindFromRequest();

        if (formPreenchido.hasErrors()) {
            List<Anuncio> resultado = DAO.findAllByClass(Anuncio.class);
            Collections.sort(resultado);

            return badRequest(index.render(resultado, false, anunciosFinalizados));
        } else {
            Anuncio newAnuncio = formPreenchido.get();

            DAO.persist(newAnuncio);
            DAO.flush();

            return anuncios();
        }
    }

    @Transactional
    public Result finalizaAnuncio(String codigo, Long id) {
        Form<String> formFinalizarPreenchido = formString.bindFromRequest();
        String codigoForm = formFinalizarPreenchido.data().get("finalizar");
        String encontrouParceiros = formFinalizarPreenchido.data().get("encontrouParceiros");

        if (codigoForm.equals(codigo)) {
            DAO.removeById(Anuncio.class, id);
            DAO.flush();

//            if (encontrouParceiros.equals("Sim")) {
//                anunciosFinalizados++;
//            }

            return anuncios();
        } else {
            List<Anuncio> resultado = DAO.findAllByClass(Anuncio.class);
            Collections.sort(resultado);

            return ok(index.render(resultado, true, anunciosFinalizados));
        }
    }

//    @Transactional
//    public static Result fazerPergunta(Long id) {
//        Form<String> formPerguntaPreenchido = formString.bindFromRequest();
//        String pergunta = formPerguntaPreenchido.data().get("pergunta");
//
//        Anuncio anuncio = DAO.findByEntityId(Anuncio.class, id);
//        anuncio.fazerPergunta(pergunta);
//
//        DAO.persist(anuncio);
//        DAO.flush();
//
//        return anuncios();
//    }
//
//    @Transactional
//    public static Result responderPergunta(Long idConversa, Long id) {
//        Form<String> formRespostaPreenchido = formString.bindFromRequest();
//        String resposta = formRespostaPreenchido.data().get("resposta");
//        String codigo = formRespostaPreenchido.data().get("codigo");
//
//        Anuncio anuncio = DAO.findByEntityId(Anuncio.class, id);
//
//        try {
//            anuncio.responderPergunta(idConversa, resposta, codigo);
//        } catch (Exception e) {
//            List<Anuncio> resultado = DAO.findAllByClass(Anuncio.class);
//            Collections.sort(resultado);
//
//            return badRequest(index.render(resultado, false, anunciosFinalizados));
//        }
//
//        DAO.persist(anuncio);
//        DAO.flush();
//
//        return anuncios();
//    }

}