package com.herokuapp.skmtodoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Integer> {
	public List<UserDetailsImpl> findByEnabled(boolean enabled);
}
