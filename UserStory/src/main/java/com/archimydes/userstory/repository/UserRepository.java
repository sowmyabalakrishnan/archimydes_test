/**
 * 
 */
package com.archimydes.userstory.repository;


import org.springframework.data.repository.CrudRepository;
import com.archimydes.userstory.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	 User findByName(String name);
}
