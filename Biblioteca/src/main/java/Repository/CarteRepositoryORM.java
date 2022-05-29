package Repository;

import Model.Carte;
import Model.Stare;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CarteRepositoryORM implements CarteRepositoryInterface {
    private SessionFactory sessionFactory;

    public CarteRepositoryORM() {
    }

    public CarteRepositoryORM(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Integer id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Carte carte = findById(id);
                System.err.println("Stergem cartea " + carte.getID());
                session.delete(carte);
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la delete carte " + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void update(Carte carte, Integer id) {
        //Stare stare = carte.getStare();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(carte);
                transaction.commit();
                //carte.setStare(stare);
            } catch (RuntimeException e) {
                System.err.println("Eroare la update carte " + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public List<Carte> findByStatus(boolean status) {
        List<Carte> carti = null;
        System.out.println("exista " + sessionFactory);
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where status = ?", Carte.class).setParameter(0, status).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by status" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findByTitlu(String titlu) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where titlu='"+titlu+"'", Carte.class).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by titlu" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findByAutor(String autor) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where autor='"+autor+"'", Carte.class).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by autor" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findByTitluAutor(String titlu, String autor) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where titlu='"+titlu+"'and autor='"+autor+"'", Carte.class).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by titlu si autor" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findByTitluStatus(String titlu, boolean status) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where status = ? and titlu = ?", Carte.class).setParameter(0, status).setParameter(1, titlu).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by titlu si status" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findByAutorStatus(String autor, boolean status) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where status = ? and autor = ?", Carte.class).setParameter(0, status).setParameter(1, autor).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by autor si status" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findByTitluAutorStatus(String titlu, String autor, boolean status) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where status = ? and titlu = ? and autor = ?", Carte.class).setParameter(0, status).setParameter(1, titlu).setParameter(2, autor).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carti by titlu, autor si status" + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findAll() {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte", Carte.class).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find all carti " + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public void add(Carte elem) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.save(elem);
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la add carte " + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public Carte findById(Integer id) {
        Carte carte = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carte = session.createQuery("from Carte where id='"+id+"'", Carte.class).setMaxResults(1).uniqueResult();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find carte by id " + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carte;
    }
}
