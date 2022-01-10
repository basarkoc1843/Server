package com.basarkocexample.server.repo;

import com.basarkocexample.server.model.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Component
public interface ServerRepo extends JpaRepository<Server,Long> {
    Server findByIpAdress(String ipAdress);
}
