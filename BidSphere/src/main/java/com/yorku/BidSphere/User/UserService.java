package com.yorku.BidSphere.User;

import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

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
		return userRepository.save(user);
	}

	public void update(int id, User user) {
		java.util.Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			User userAttributes = existingUser.get();

			userAttributes.setUserName(user.getUserName());
			userAttributes.setPassword(user.getPassword());
			userAttributes.setFirstName(user.getFirstName());
			userAttributes.setLastName(user.getLastName());
			userAttributes.setStreetNumber(user.getStreetNumber());
			userAttributes.setStreetAddress(user.getStreetAddress());
			userAttributes.setCity(user.getCity());
			userAttributes.setProvince(user.getProvince());
			userAttributes.setPostalCode(user.getPostalCode());
			userAttributes.setCountry(user.getCountry());

			userRepository.save(userAttributes);
		} else {
			throw new IllegalArgumentException("No such id:	" + id);
		}
	}

	public void delete(int id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("User not found with id: " + id);
		}
	}

}
