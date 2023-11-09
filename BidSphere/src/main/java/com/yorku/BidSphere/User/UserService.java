package com.yorku.BidSphere.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

	public List<User> readAll() {
		String sql = "SELECT id, userName, password, firstName, lastName, streetNumber, streetAddress, city, province, postalCode, country FROM users";
		List<User> users = new ArrayList<>();
		try (Connection conn = DatabaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setStreetNumber(rs.getInt("streetNumber"));
				user.setStreetAddress(rs.getString("streetAddress"));
				user.setCity(rs.getString("city"));
				user.setProvince(rs.getString("province"));
				user.setPostalCode(rs.getString("postalCode"));
				user.setCountry(rs.getString("country"));

				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

	public void create(User user) {
		String sql = "INSERT INTO users(userName, password, firstName, lastName, streetNumber, streetAddress, city, province, postalCode, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setInt(5, user.getStreetNumber());
			pstmt.setString(6, user.getStreetAddress());
			pstmt.setString(7, user.getCity());
			pstmt.setString(8, user.getProvince());
			pstmt.setString(9, user.getPostalCode());
			pstmt.setString(10, user.getCountry());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public User read(int id) {
		String sql = "SELECT userName, password, firstName, lastName, streetNumber, streetAddress, city, province, postalCode, country FROM users WHERE id = ?";
		User user = null;

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new User();

					user.setId(id);
					user.setUserName(rs.getString("userName"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setStreetNumber(rs.getInt("streetNumber"));
					user.setStreetAddress(rs.getString("streetAddress"));
					user.setCity(rs.getString("city"));
					user.setProvince(rs.getString("province"));
					user.setPostalCode(rs.getString("postalCode"));
					user.setCountry(rs.getString("country"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	public void update(int id, User user) {
		String sql = "UPDATE users SET userName = ?, password = ?, firstName = ?, lastName = ?, streetNumber = ?, streetAddress = ?, city = ?, province = ?, postalCode = ?, country = ? WHERE id = ?";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setInt(5, user.getStreetNumber());
			pstmt.setString(6, user.getStreetAddress());
			pstmt.setString(7, user.getCity());
			pstmt.setString(8, user.getProvince());
			pstmt.setString(9, user.getPostalCode());
			pstmt.setString(10, user.getCountry());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM users WHERE id = ?";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	// change db and deal w attributes restrictions

}