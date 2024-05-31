
package com.janu.walletproject.repository;


import com.janu.walletproject.model.CurrentAdminSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tejas
 *
 */

@Repository
public interface CurrentAdminSessionRepo extends JpaRepository<CurrentAdminSession, Integer> {

	public Optional<CurrentAdminSession> findByKey(String key);

	public Optional<CurrentAdminSession> findByAdminId(Integer adminId);

}
