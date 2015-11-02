package models;

/**
 * Created by arthur on 02/11/15.
 */

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;

@Entity
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String pergunta;

    @Column
    private String resposta;

    public Chat() {

    }

    public Chat(String pergunta) throws Exception {
        validaString(pergunta);

        this.pergunta = pergunta;
    }

    private void validaString(String sentenca) throws Exception {
        if (sentenca == null || sentenca.trim() == "") {
            throw new Exception("A conversa precisa ter uma pergunta.");
        }
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) throws Exception {
        validaString(pergunta);

        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) throws Exception {
        validaString(resposta);

        this.resposta = resposta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
