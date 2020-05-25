/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.repositories;

import com.nightm4re.resfulserver.model.Server;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nightm4re
 */
@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    
    @Query(
    value="SELECT * FROM server AS i WHERE i.ip LIKE %?1%",
            nativeQuery=true)
    public Set<Server> findByIp(String ip);  
        
    @Query(
    value="SELECT * FROM server AS i WHERE i.name LIKE %?1%",
            nativeQuery=true)
    public Set<Server> findByName(String name);
}
