package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;

public class HoldingDAO {
	private static final String STOCK_HOLDING = "stock_holding";

	public static Holding retrieveIndividualHolding(Order order) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Holding holding = null;
		
		final String retrieveHoldingSQL = "Select holding_id, stock_symbol, remaining_quantity, price_paid, currency from " +
										   STOCK_HOLDING + " where user_id = ? and stock_symbol in ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveHoldingSQL);
			ps.setLong(1, order.getUserID());
			ps.setString(2, order.getStockSymbol());
			rs = ps.executeQuery();
			rs.next();
			holding = new Holding(rs.getLong("holding_id"),
								  order.getUserID(),
								  rs.getString("stock_symbol"),
								  rs.getInt("remaining_quantity"),
								  rs.getDouble("price_paid"),
								  rs.getString("currency"));
			
			return holding;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	}
	
	public static List<Holding> retrieveHolding(long userId) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Holding holding = null;
		List<Holding> holdingList = new ArrayList<>();
		final String retrieveHoldingSQL = "Select holding_id, stock_symbol, remaining_quantity, price_paid, currency from " +
										   STOCK_HOLDING + " where user_id = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveHoldingSQL);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				holding = new Holding(rs.getLong("holding_id"),
									  userId,
									  rs.getString("stock_symbol"),
									  rs.getInt("remaining_quantity"),
									  rs.getDouble("price_paid"),
									  rs.getString("currency"));
				holdingList.add(holding);
			}
			return holdingList;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	}
	
	public static void updateHolding(Holding holding) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String updateHoldingSQL = "Update " + STOCK_HOLDING + " set price_paid = ?, remaining_quantity = ? where user_id = ? and stock_symbol = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateHoldingSQL);
			ps.setDouble(1, holding.getPricePaid());
			ps.setInt(2, holding.getRemainingQuantity());
			ps.setLong(3, holding.getUserId());
			ps.setString(4, holding.getStockSymbol());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
	public static void storeHolding(Holding holding) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String storeHoldingSQL = "Insert into " + STOCK_HOLDING + " values (holding_id.nextVal, ?, ?, ?, ?, ?)";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(storeHoldingSQL);
			ps.setLong(1, holding.getUserId());
			ps.setString(2, holding.getStockSymbol());
			ps.setInt(3, holding.getRemainingQuantity());
			ps.setDouble(4, holding.getPricePaid());
			ps.setString(5, holding.getCurrency());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
public static void removeHolding(Order order) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String removeHoldingSQL = "Delete from " + STOCK_HOLDING + " where user_id = ? and stock_symbol in ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(removeHoldingSQL);
			ps.setLong(1, order.getUserID());
			ps.setString(2, order.getStockSymbol());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}

	public static void main(String[] args) throws Exception{

		//Testing retrieveIndividualHolding
		/*int holding = HoldingDAO.retrieveIndividualHolding(1L, "AAPL");
		System.out.println(holding);*/
		
		//Testing storeHolding
		/*Holding holding = new Holding(6, "APPL", 100, 99.99, "SGD");
		System.out.println(holding);
		HoldingDAO.storeHolding(holding);*/
		
		//Testing removeHolding
		/*Order order = new Order(6, TransactionType.Sell, 50, "APPL", Term.GoodForDay, PriceType.Market, 9.99, OrderStatus.Pending);
		HoldingDAO.removeHolding(order);*/
		
		//Testing updateHolding
		/*Holding holding = new Holding(10, "AAPL", 222, 99.99, "SGD");
		System.out.println(holding);
		HoldingDAO.updateHolding(holding);*/
	}

}
