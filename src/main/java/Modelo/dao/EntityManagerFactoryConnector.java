package Modelo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryConnector {

    private static final String PERSISTENCE_UNIT_NAME = "Biblioteca";
    private static EntityManagerFactory emf;

    // Metodo para inicializar el EntityManagerFactory si no está inicializado
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

    // Metodo para obtener un EntityManager
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    // Metodo para cerrar el EntityManagerFactory al final de la aplicación
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
