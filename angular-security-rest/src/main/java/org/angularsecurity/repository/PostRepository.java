package org.angularsecurity.repository;

import org.angularsecurity.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Employee, Long>{
}
