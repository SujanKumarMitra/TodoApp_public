package com.herokuapp.skmtodoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Integer> {
	
	
}
