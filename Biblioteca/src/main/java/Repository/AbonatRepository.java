package Repository;

import Model.Abonat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AbonatRepository implements AbonatRepositoryInterface {

    private JdbcUtils dbUtils;

    public AbonatRepository(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public Abonat findByCod(Integer cod) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Abonati where cod = ?")) {
            preStnt.setInt(1, cod);
            try(ResultSet result = preStnt.executeQuery()) {
                if (result.next()) {
                    String cnp = result.getString("cnp");
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    String adresa = result.getString("adresa");
                    String telefon = result.getString("telefon");
                    String parola = result.getString("parola");
                    Abonat abonat = new Abonat(cnp, nume, prenume, adresa, telefon, cod, parola);
                    return abonat;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public void add(Abonat elem) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("insert into Abonati (cnp, nume, prenume, adresa, telefon, cod, parola) values (?, ?, ?, ?, ?, ?, ?)")) {
            preStnt.setString(1, elem.getID());
            preStnt.setString(2, elem.getNume());
            preStnt.setString(3, elem.getPrenume());
            preStnt.setString(4, elem.getAdresa());
            preStnt.setString(5, elem.getTelefon());
            preStnt.setInt(6, elem.getCod());
            preStnt.setString(7, elem.getParola());
            preStnt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public Abonat findById(String id) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Abonati where cnp = ?")) {
            preStnt.setString(1, id);
            try(ResultSet result = preStnt.executeQuery()) {
                if (result.next()) {
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    String adresa = result.getString("adresa");
                    String telefon = result.getString("telefon");
                    Integer cod = result.getInt("cod");
                    String parola = result.getString("parola");
                    Abonat abonat = new Abonat(id, nume, prenume, adresa, telefon, cod, parola);
                    return abonat;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }
}
