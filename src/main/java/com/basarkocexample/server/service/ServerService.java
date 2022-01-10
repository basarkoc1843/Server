package com.basarkocexample.server.service;

import com.basarkocexample.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Collection<Server> list(int limit);
    Server ping(String ipAddress) throws IOException;

    Server get(Long id);
    Boolean delete(Long id);
    Server update(Server server);

}
