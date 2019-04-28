package com.ssa.usa.federal.gov.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssa.usa.federal.gov.entity.StateListEntity;

@Repository
public interface StateListRepository extends JpaRepository<StateListEntity,Serializable> {

}
