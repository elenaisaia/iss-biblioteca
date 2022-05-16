package Repository;

import Model.Abonat;
import Model.Carte;
import Model.Stare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarteRepository implements CarteRepositoryInterface {

    private JdbcUtils dbUtils;

    public CarteRepository(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public void delete(Integer id) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("delete from Carti where cod = ?")) {
            preStnt.setInt(1, id);
            preStnt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public void update(Carte elem, Integer id) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("update table Carti set titlu = ?, autor = ?, status = ?, stare = ? where cod = ?")) {
            preStnt.setString(1, elem.getTitlu());
            preStnt.setString(2, elem.getAutor());
            preStnt.setBoolean(3, elem.getStatus());
            preStnt.setString(4, elem.getStare().toString());
            preStnt.setInt(5, id);
            preStnt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public List<Carte> findByStatus(boolean status) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where status = ?")) {
            preStnt.setBoolean(1, status);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String titlu = result.getString("titlu");
                    String autor = result.getString("autor");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findByTitlu(String titlu) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where titlu = ?")) {
            preStnt.setString(1, titlu);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String autor = result.getString("autor");
                    boolean status = result.getBoolean("status");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findByAutor(String autor) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where autor = ?")) {
            preStnt.setString(1, autor);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String titlu = result.getString("titlu");
                    boolean status = result.getBoolean("status");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findByTitluAutor(String titlu, String autor) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where titlu = ? and autor = ?")) {
            preStnt.setString(1, titlu);
            preStnt.setString(2, autor);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    boolean status = result.getBoolean("status");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findByTitluStatus(String titlu, boolean status) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where titlu = ? and status = ?")) {
            preStnt.setString(1, titlu);
            preStnt.setBoolean(2, status);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String autor = result.getString("autor");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findByAutorStatus(String autor, boolean status) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where autor = ? and status = ?")) {
            preStnt.setString(1, autor);
            preStnt.setBoolean(2, status);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String titlu = result.getString("titlu");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findByTitluAutorStatus(String titlu, String autor, boolean status) {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where titlu = ? and autor = ? and status = ?")) {
            preStnt.setString(1, titlu);
            preStnt.setString(2, autor);
            preStnt.setBoolean(3, status);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public List<Carte> findAll() {
        Connection con = dbUtils.getConnection();
        List<Carte> list = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti")) {
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    String titlu = result.getString("titlu");
                    String autor = result.getString("autor");
                    boolean status = result.getBoolean("status");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(cod, titlu, autor, status, Stare.getStare(stare));
                    list.add(carte);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return list;
    }

    @Override
    public void add(Carte elem) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("insert into Carti (cod, titlu, autor, status, stare) values (?, ?, ?, ?, ?)")) {
            preStnt.setInt(1, elem.getID());
            preStnt.setString(2, elem.getTitlu());
            preStnt.setString(3, elem.getAutor());
            preStnt.setBoolean(4, elem.getStatus());
            preStnt.setString(5, elem.getStare().toString());
            preStnt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public Carte findById(Integer id) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Carti where cod = ?")) {
            preStnt.setInt(1, id);
            try(ResultSet result = preStnt.executeQuery()) {
                if (result.next()) {
                    String titlu = result.getString("titlu");
                    String autor = result.getString("autor");
                    boolean status = result.getBoolean("status");
                    String stare = result.getString("stare");
                    Carte carte = new Carte(id, titlu, autor, status, Stare.getStare(stare));
                    return carte;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }
}
