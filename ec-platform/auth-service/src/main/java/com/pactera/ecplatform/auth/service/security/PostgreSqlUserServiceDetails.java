package com.pactera.ecplatform.auth.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pactera.ecplatform.auth.domain.Users;
import com.pactera.ecplatform.auth.repository.UserRepository;
import com.pactera.ecplatform.auth.service.UserAdapter;

@Service("customUserDetailsService")
public class PostgreSqlUserServiceDetails implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public PostgreSqlUserServiceDetails(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s doesn't exist!", username));
		}
		return new UserAdapter(user);
	}

	public Users saveUser(Users user) {
		return userRepository.save(user);
	}

}
