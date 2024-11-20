package com.ssafy.Tteonaso.repository;

import com.ssafy.Tteonaso.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapFilterRepository extends JpaRepository<Address, String> {

}
