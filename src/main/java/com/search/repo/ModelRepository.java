package com.search.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.search.domain.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

	@Query("select m from Model m where m.name like :name% ")
	List<Model> findByNameStartsWith(@Param("name") String name);
}
