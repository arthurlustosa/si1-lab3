package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Anuncio implements Comparable<Anuncio> {

	@Id
    @GeneratedValue
	private Long id;
	@Column
	private String descricao;
	@Column
	private String titulo;
	@Column
	private String cidade;
	@Column
	private String bairro;
	@Column
	private String instrumentos;
	@Column
	private String estilosBanidos;
	@Column
	private String estilos;
	@Column
	private String contatos;
	@Column
	private String codigo;
	@Column
	private String interesses;
	@OneToMany(cascade = CascadeType.ALL)

	private List<Chat> conversas;

	@Temporal(TemporalType.DATE)
	private Date data = new Date();

	public Anuncio() {
		this.conversas = new ArrayList<>();
	}

	public Anuncio(String titulo, String descricao, String cidade,
			String bairro, String instrumentos, String estilos,
			String estilosBanidos, String contatos, String interesses, String codigo)
			throws Exception {
		verificaValidadeDosParametros(titulo, descricao, cidade, bairro, instrumentos,
				contatos, interesses, codigo);
		this.titulo = titulo;
		this.descricao = descricao;
		this.cidade = cidade;
		this.bairro = bairro;
		this.instrumentos = instrumentos;
		this.estilos = estilos;
		this.estilosBanidos = estilosBanidos;
		this.contatos = contatos;
		this.interesses = interesses;
		this.codigo = codigo;
		this.conversas = new ArrayList<>();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getData(){
		return data;
	}
	
	public void setData(Date data){
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

 	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

	public String getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(String instrumentos) {
		this.instrumentos = instrumentos;
	}

	public String getEstilosBanidos() {
		return estilosBanidos;
	}

	public void setEstilosBanidos(String estilosBanidos) {
		this.estilosBanidos = estilosBanidos;
	}

	public String getEstilos() {
		return estilos;
	}

	public void setEstilos(String estilos) {
		this.estilos = estilos;
	}

	public String getContatos() {
		return contatos;
	}

	public void setContatos(String contatos) {
		this.contatos = contatos;
	}

	public String getInteresses() {
		return interesses;
	}

	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}

 	public boolean isEstilosEmpty() {
        return this.estilos.length() == 0;
    }

    public boolean isEstilosBanidosEmpty() {
        return this.estilosBanidos.length() == 0;
    }

	private void verificaValidadeDosParametros(String titulo, String descricao,
			String cidade, String bairro, String instrumentos, String contatos,
			String interesses, String codigo) throws Exception {
		if (titulo == null || titulo.equals("")) {
			throw new Exception(
					"O titulo nao pode ser nulo ou vazio para criacao de um anuncio");
		}
		if (descricao == null || descricao.equals("")) {
			throw new Exception(
					"A descricao nao pode ser nula ou vazia para criacao de um anuncio");
		}
		if (cidade == null || cidade.equals("")) {
			throw new Exception(
					"A cidade nao pode ser nula ou vazia para criacao de um anuncio");
		}
		if (bairro == null || bairro.equals("")) {
			throw new Exception(
					"O bairro nao pode ser nulo ou vazio para criacao de um anuncio");
		}
		if (instrumentos == null || instrumentos.equals("")) {
			throw new Exception(
					"Os intrumentos nao podem ser invalidos para criacao de um anuncio");
		}
		if (contatos == null || contatos.equals("")) {
			throw new Exception(
					"Os contatos nao podem ser invalidos para criacao de um anuncio");
		}
		if (interesses == null || interesses.equals("")) {
			throw new Exception(
					"Os interesses nao podem ser invalidos para criacao de um anuncio");
		}
	}

	public void fazerPergunta(String pergunta) {
		try {
			conversas.add(new Chat(pergunta));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void responderPergunta(Long idConversa, String resposta, String codigo) throws Exception {
		if (!codigo.equals(this.codigo)) {
			throw new Exception("Somente quem tem o codigo do anúncio pode responder perguntas.");
		}

		for (Chat conversa : conversas) {
			if (conversa.getId().equals(idConversa)) {
				try {
					conversa.setResposta(resposta);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public List<Chat> getConversas() {
		return conversas;
	}

	public void setConversas(List<Chat> conversas) {
		this.conversas = conversas;
	}

	@Override
	public int compareTo(Anuncio o) {
        return getData().compareTo(o.getData()) * (-1);

	}

}
