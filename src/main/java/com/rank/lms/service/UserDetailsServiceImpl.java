package com.rank.lms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rank.lms.DTO.UserDetailsImpl;
import com.rank.lms.Repository.UserRepository;
import com.rank.lms.entity.User;
import com.rank.lms.exception.ErrorCodeHelper;
import com.rank.lms.exception.ErrorConstant;
import com.rank.lms.exception.ErrorInfo;
import com.rank.lms.exception.ServiceException;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(userEmail);
		if (user.isEmpty()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1008_ERROR_CODE,
					ErrorConstant.E1008_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo);
		}
		return UserDetailsImpl.build(user.get());
	}

}
