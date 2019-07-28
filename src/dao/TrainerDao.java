package dao;

import entities.Trainer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainerDao extends Dao {

    private final String getTrainers = "select*from Trainer;";
    private final String getTrainersById = "select * from trainer where tid= ?";
    private final String insertTrainer = "insert into trainer(tfname,tlname,tsubject) values(?, ?, ?)";

    public List<Trainer> getTrainer() {

        List<Trainer> trainers = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTrainers);
            while (rs.next()) {
                Trainer s = new Trainer();
                s.setTid(rs.getInt(1));
                s.setfirstName(rs.getString(2));
                s.setlastName(rs.getString(3));
                s.setSubject(rs.getString(4));
                trainers.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trainers;

    }

    public Trainer getTrainerById(int id) {
        Trainer s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getTrainersById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            s = new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }

    public boolean insertTrainer(Trainer s) {
        boolean inserted = false;
        
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainer);
            pst.setString(1, s.getfirstName());
            pst.setString(2, s.getlastName());
            pst.setString(3, s.getSubject());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Trainer inserted successfully");
            } else {
                System.out.println("Trainer not inserted");
            }
            pst.close();
            closeConnections(pst);

        } catch (SQLException ex) {
            System.out.println("Something went wrong..");
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return inserted;
    }
}
