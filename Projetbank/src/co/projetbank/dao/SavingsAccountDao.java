package co.projetbank.dao;
import co.projetbank.entities.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SavingsAccountDao extends Dao<SavingsAccount>{

	@Override
	public SavingsAccount find(int id) {
		String str = "select * from T_SavingsAccount where IdCust=?";
		PreparedStatement ps;
		SavingsAccount compte = null;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,id);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				compte = new SavingsAccount(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getDate(3),resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return compte;
	}

	@Override
	public boolean create(SavingsAccount obj) {
		String str = "INSERT INTO T_SavingsAccount (IdCust,Balance,CreationDate,InterestRate) VALUES (?, ? ,? ,?);";
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1, obj.getIdCust());
			ps.setDouble(2,obj.getBalance());
			ps.setDate(3,(Date) obj.getDateCreation());
			ps.setDouble(4, obj.getInterestRate());
			ps.executeQuery();
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean update(SavingsAccount obj) {		
		String str = " update T_SavingsAccount set Balance=? where IdCust=?;";		
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setDouble(1,obj.getBalance());
			ps.setInt(2,obj.getIdCust());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ok;
	}

	@Override
	public boolean delete(SavingsAccount obj) {
		String str = "delete from T_SavingsAccount where IdCust=?;";	
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,obj.getIdCust());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
}
