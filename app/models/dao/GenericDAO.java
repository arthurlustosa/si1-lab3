package models.dao;

import java.util.List;

import javax.persistence.Query;

import play.db.jpa.JPA;

/**
 * Camada genÃ©rica para acesso ao Banco de Dados
 */
public class GenericDAO {

    /**
     * Salva um objeto no BD.
     */
    public boolean persist(Object e) {
        JPA.em().persist(e);
        return true;
    }

    /**
     * ForÃ§a que as operaÃ§Ãµes executadas atÃ© agora no cÃ³digo sejam
     * executadas no BD. Normalmente, o final de cada bloco @Transactional
     * farÃ¡ o flush. PorÃ©m, se vocÃª quer controlar erros, melhor fazer antes.
     */
    public void flush() {
        JPA.em().flush();
    }

    /**
     * Atualiza a informaÃ§Ã£o da entidade do cÃ³digo com a do banco de dados.
     * <p>
     * Mais sobre merge: http://www.arquiteturacomputacional.eti.br/2013/02/entenda-o-ciclo-de-vida-das-entidades.html
     */
    public void merge(Object e) {
        JPA.em().merge(e);
    }

    /**
     * Procura uma certa {@code clazz} pelo seu {@code id}.
     */
    public <T> T findByEntityId(Class<T> clazz, Long id) {
        return JPA.em().find(clazz, id);
    }

    /**
     * Procura todos os objetos de uma certa classe pelo seu {@code className}
     * descrito na Entidade.
     */
    public <T> List<T> findAllByClass(Class clazz) {
        String hql = "FROM " + clazz.getName();
        Query hqlQuery = JPA.em().createQuery(hql);
        return hqlQuery.getResultList();
    }

    /**
     * Deleta do banco de dados uma {@code classe} referenciada pelo seu
     * {@code id}.
     */
    public <T> void removeById(Class<T> classe, Long id) {
        JPA.em().remove(findByEntityId(classe, id));
    }

    /**
     * Remove o respectivo {@code objeto} do banco de dados.
     */
    public void remove(Object objeto) {
        JPA.em().remove(objeto);
    }

    /**
     * Procura uma certa {@code className} pelo seu {@code attributeName}.
     */
    public <T> List<T> findByAttributeName(String className,
                                           String attributeName, String attributeValue) {
        String hql = "FROM " + className + " c" + " WHERE c." + attributeName
                + " = '" + attributeValue + "'";
        Query hqlQuery = JPA.em().createQuery(hql);
        return hqlQuery.getResultList();
    }

    private Query createQuery(String query) {
        return JPA.em().createQuery(query);
    }
}
