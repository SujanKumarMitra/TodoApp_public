package com.herokuapp.skmtodoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;
import com.herokuapp.skmtodoapp.repository.UserDetailsRepository;

@Service
public class SchedulerService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Scheduled(cron = "${account.unverified.delete.frequency}" )
	public void deleteUnverifiedAccounts() {
		
		List<UserDetailsImpl> userDetailsImpls = userDetailsRepository.findByEnabled(false);
		for(UserDetailsImpl userDetails: userDetailsImpls) {
			userDetailsRepository.delete(userDetails);
		}
	}
}
