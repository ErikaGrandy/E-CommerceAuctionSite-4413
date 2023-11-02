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

}
