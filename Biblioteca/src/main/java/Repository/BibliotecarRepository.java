package Repository;

import Model.Bibliotecar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BibliotecarRepository implements BibliotecarRepositoryInterface {
    private JdbcUtils dbUtils;

    public BibliotecarRepository(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public void add(Bibliotecar elem) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("insert into Bibliotecari (cnp, nume, prenume, parola) values (?, ?, ?, ?)")) {
            preStnt.setString(1, elem.getID());
            preStnt.setString(2, elem.getNume());
            preStnt.setString(3, elem.getPrenume());
            preStnt.setString(4, elem.getParola());
            preStnt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public Bibliotecar findById(String id) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Bibliotecari where cnp = ?")) {
            preStnt.setString(1, id);
            try(ResultSet result = preStnt.executeQuery()) {
                if (result.next()) {
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    String parola = result.getString("parola");
                    Bibliotecar bibliotecar = new Bibliotecar(id, nume, prenume, parola);
                    return bibliotecar;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }
}
