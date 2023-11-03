package com.yorku.BidSphere.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

	public List<User> readAll() {
		String sql = "SELECT userName, password, firstName, lastName, streetAddress, streetNumber, city, province, postalCode, country FROM users";

		List<User> users = new ArrayList<>();

		try (Connection conn = DatabaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				User user = new User();
				Address address = new Address();
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				address.setStreetAddress(rs.getString("streetAddress"));
				address.setStreetNumber(rs.getInt("streetNumber"));
				address.setCity(rs.getString("city"));
				address.setProvince(rs.getString("province"));
				address.setPostalCode(rs.getString("postalCode"));
				address.setCountry(rs.getString("country"));

				user.setAddress(address);

				users.add(user);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return users;

	}

	public void create(User user) {
		String sql = "INSERT INTO users(userName, password, firstName, lastName, streetAddress, streetNumber, city, province, postalCode, country) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.getAddress().getStreetAddress());
			pstmt.setInt(6, user.getAddress().getStreetNumber());
			pstmt.setString(7, user.getAddress().getCity());
			pstmt.setString(8, user.getAddress().getProvince());
			pstmt.setString(9, user.getAddress().getPostalCode());
			pstmt.setString(10, user.getAddress().getCountry());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public User read(String userName) {
		String sql = "SELECT userName, password, firstName, lastName, streetAddress, streetNumber, city, province, postalCode, country FROM users WHERE userName = ?";
		User user = null;
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameter
			pstmt.setString(1, userName);
			// Execute the query and get the result set
			try (ResultSet rs = pstmt.executeQuery()) {
				// Check if a result was returned
				if (rs.next()) {
					user = new User();
					Address address = new Address();
					// set the properties of the student object
					user.setUserName(userName);
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					address.setStreetAddress(rs.getString("streetAddress"));
					address.setStreetNumber(rs.getInt("streetNumber"));
					address.setCity(rs.getString("city"));
					address.setProvince(rs.getString("province"));
					address.setPostalCode(rs.getString("postalCode"));
					address.setCountry(rs.getString("country"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return user;
	}

	public void update(String userName, User user) {
		String sql = "UPDATE users SET password = ?, firstName = ?, lastName = ?, streetAddress = ?, streetNumber = ?, city =?, province = ?, postalCode = ?, country = ? WHERE userName = ?";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameters
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.getAddress().getStreetAddress());
			pstmt.setInt(6, user.getAddress().getStreetNumber());
			pstmt.setString(7, user.getAddress().getCity());
			pstmt.setString(8, user.getAddress().getProvince());
			pstmt.setString(9, user.getAddress().getPostalCode());
			pstmt.setString(10, user.getAddress().getCountry());

			// Update the student record
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void delete(String userName) {
		String sql = "DELETE FROM users WHERE userName = ?";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameter
			pstmt.setString(1, userName);
			// Delete the student record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
