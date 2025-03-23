package com.javaweb.Java_web.repository;

import com.javaweb.Java_web.entity.AccountAdmin;
import com.javaweb.Java_web.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountAdminRepository extends JpaRepository<AccountAdmin , Long> {
    // get all category .
    List<AccountAdmin> findAllByDeletedFalse() ;

    // get one category by id .
    Optional<AccountAdmin> findByAccountIdAndDeletedFalse(Long AccountId) ;

    // get one category by name .
    Optional<AccountAdmin> findByLoginNameAndDeletedFalse(String loginName) ;
    // check name category is exists ?
    boolean existsByLoginName(String loginName) ;
}
