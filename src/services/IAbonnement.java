package services;
import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author macbook
 */
public interface IAbonnement<T> {

    public void ajouter_ab(T a) throws SQLException;
    public List<T> afficher_ab() throws SQLException;

    
}
