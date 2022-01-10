package com.basarkocexample.server.service.implementation;

import com.basarkocexample.server.enumarition.Status;
import com.basarkocexample.server.model.Server;
import com.basarkocexample.server.repo.ServerRepo;
import com.basarkocexample.server.service.ServerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;


@Service
@Slf4j
public class ServerImpl implements ServerService{

    @Autowired
    private final ServerRepo serverRepo;

    public ServerImpl(ServerRepo serverRepo) {
        this.serverRepo = serverRepo;
    }

    @Override
    public Server create(Server server) {

        //log.info("Saving new server : {} ",server.getName());

        server.setImageUrl(setImageUrls());

        return serverRepo.save(server);

    }


    @Override
    public Server ping(String ipAddress) throws IOException {

        log.info("Pingin server P: {}",ipAddress);
        Server server=serverRepo.findByIpAdress(ipAddress);
        InetAddress address=InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP:Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;


    }

    @Override
    public Boolean delete(Long id) {

        log.info("Deleting server : {}",id);

         serverRepo.deleteById(id);

         return Boolean.TRUE;



    }

    @Override
    public Collection<Server> list(int limit) {

        log.info("Fetching all  servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();



    }

    @Override
    public Server get(Long id) {

        log.info("Fetching Server By Id :{}",id);
        return serverRepo.findById(id).get();


    }

    @Override
    public Server update(Server server) {

        log.info("Updating Server : {}",server.getName());

        return serverRepo.save(server);



    }
    private String setImageUrls() {

        String[] serverNames={"server1.png","server2.png","server3.png","server4.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+serverNames[new Random().nextInt(4)]).toUriString();



    }


}
