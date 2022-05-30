package Repository;

import Model.Abonat;
import Model.Carte;
import Model.Imprumut;
import Model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImprumutRepository implements ImprumutRepositoryInterface {
    private JdbcUtils dbUtils;

    public ImprumutRepository(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public void update(Imprumut imprumut, Integer id) {
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("update Imprumuturi set cnp=?, data_imprumut=?, data_restituire=?, status=? where id_imprumut=? "))
        {
            preparedStatement.setString(1, imprumut.getAbonat());
            preparedStatement.setString(2, imprumut.getDataImprumut().toString());
            preparedStatement.setString(3, imprumut.getDataRestituire().toString());
            preparedStatement.setString(4, imprumut.getStatus().toString());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.err.println("Error DB" + ex);
        }
    }

    @Override
    public List<Imprumut> findAll() {
        Connection con = dbUtils.getConnection();
        List<Imprumut> imprumuturi = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from Imprumuturi")) {
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer id_imprumut = result.getInt("id_imprumut");
                    String cnp = result.getString("cnp");
                    LocalDate data_imprumut = LocalDate.parse(result.getString("data_imprumut"));
                    LocalDate data_restituire = null;
                    String data = result.getString("data_restituire");
                    if(data != null) {
                        data_restituire = LocalDate.parse(data);
                    }
                    String status = result.getString("status");
                    Imprumut imprumut = new Imprumut(id_imprumut, cnp, null, data_imprumut, data_restituire, Status.getStatus(status));
                    imprumuturi.add(imprumut);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return imprumuturi;
    }

    @Override
    public void add(Imprumut elem) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("insert into Imprumuturi (cnp, data_imprumut, status) values (?, ?, ?)")) {
            preStnt.setString(1, elem.getAbonat());
            preStnt.setString(2, elem.getDataImprumut().toString());
            preStnt.setString(3, elem.getStatus().toString());
            int result = preStnt.executeUpdate();
            if (result > 0){
                //obtinem ID-ul generat de baza de date
                ResultSet rs = preStnt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    elem.setID(id);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public void addIC(Imprumut elem, Carte carte) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("insert into ImprumuturiCarti (id_imprumut, cod) values (?, ?)")) {
            preStnt.setInt(1, elem.getID());
            preStnt.setInt(2, carte.getID());
            int result = preStnt.executeUpdate();
//            if (result > 0){
//                //obtinem ID-ul generat de baza de date
//                ResultSet rs = preStnt.getGeneratedKeys();
//                if (rs.next()) {
//                    int id = rs.getInt(1);
//                    elem.setID(id);
//                }
//            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public List<Integer> findByImprumut(Integer id) {
        Connection con = dbUtils.getConnection();
        List<Integer> carti = new ArrayList<>();
        try(PreparedStatement preStnt = con.prepareStatement("select * from ImprumuturiCarti where id_imprumut = ?")) {
            preStnt.setInt(1, id);
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    Integer cod = result.getInt("cod");
                    carti.add(cod);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return carti;
    }

    @Override
    public Imprumut findById(Integer id) {
        return null;
    }
}
