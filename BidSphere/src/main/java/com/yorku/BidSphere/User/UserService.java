package com.yorku.BidSphere.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	public boolean isPasswordValid(String password) {
		return pattern.matcher(password).matches();
	}

	public ArrayList<User> readAll() {
		ArrayList<User> usersList = new ArrayList<>();
		Iterable<User> iteratorUsersList = userRepository.findAll();
		if (iteratorUsersList != null) {
			Iterator<User> iterator = iteratorUsersList.iterator();
			while (iterator.hasNext()) {
				usersList.add(iterator.next());
			}
			return usersList;
		} else {
			return null;
		}
	}

	public User read(int id) {
		java.util.Optional<User> user = userRepository.findById(id);
		return (user.isPresent() ? user.get() : null);
	}

	public User create(User user) {
		String password = user.getPassword();
		if (isPasswordValid(password)) {
			return userRepository.save(user);
		}
		return null;
	}

	public void update(int id, User user) {
		java.util.Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			User userAttributes = existingUser.get();

			userAttributes.setUserName(user.getUserName());
			userAttributes.setFirstName(user.getFirstName());
			userAttributes.setLastName(user.getLastName());
			userAttributes.setStreetNumber(user.getStreetNumber());
			userAttributes.setStreetAddress(user.getStreetAddress());
			userAttributes.setCity(user.getCity());
			userAttributes.setProvince(user.getProvince());
			userAttributes.setPostalCode(user.getPostalCode());
			userAttributes.setCountry(user.getCountry());

			String updatePassword = user.getPassword();
			if (isPasswordValid(updatePassword)) {
				userAttributes.setPassword(updatePassword);
			}

			userRepository.save(userAttributes);
		}
	}

	public void delete(int id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("User not found with id: " + id);
		}
	}

	public User verify(String username, String password) {
		User user = userRepository.getUserByUserNameAndPassword(username, password);
		if (user == null) {
			return null;
		}

		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}

	public boolean checkExisting(String username) {
		int count = userRepository.countUserByUserName(username);
		if (count >= 1) {
			return true;
		}
		return false;
	}

	public User updatePassword(String username, int streetNumber, String newPassword) {
		User user = userRepository.getUserByUserName(username);
		if (user == null || user.getStreetNumber() != streetNumber || !isPasswordValid(newPassword)) {
			return null;
		}
		user.setPassword(newPassword);
		user = userRepository.save(user);
		return user;
	}

}
