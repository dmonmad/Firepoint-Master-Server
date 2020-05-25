/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.services;

/**
 *
 * @author Nightm4re
 */
import com.nightm4re.resfulserver.exceptions.RecordNotFoundException;
import com.nightm4re.resfulserver.model.Server;
import com.nightm4re.resfulserver.repositories.ServerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    @Autowired
    ServerRepository repository;

    public Set<Server> getAllServers() {
//        Server server1 = new Server(new Long(0), "wtf", "192.168.1.1", 9000);
//        Server server2 = new Server(new Long(1), "goodjob", "251.41.57.133", 1900);
//        Set<Server> serverList = new HashSet<Server>() {
//            {
//                add(server1);
//                add(server2);
//            }
//        };
        Set<Server> serverList = repository.findAll().stream().collect(Collectors.toSet());

        for (Server s : serverList) {
            System.out.println(s.toString());
        }

        if (serverList.size() > 0) {
            return serverList;
        } else {
            Set<Server> s = new ArrayList<Server>().stream().collect(Collectors.toSet());
            return s;
        }
    }

    public Server getServerByIp(String ip) throws RecordNotFoundException {
        Set<Server> server = repository.findByIp(ip);

        if (server.size() > 0) {
            Iterator c = server.iterator();
            return (Server) c.next();
        } else {
            throw new RecordNotFoundException("No server record exist for given ip ", ip);
        }
    }

    public Server createServer(Server entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Server updateServer(Server entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Set<Server> server = repository.findByIp(entity.getIp());

            if (server.size() > 0) {
                Iterator c = server.iterator();
                Server newEntity = (Server) c.next();
                newEntity.setServername(entity.getServername());
                newEntity.setIp(entity.getIp());
                newEntity.setLastupdate(entity.getLastupdate());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Server not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of server given", 0l);
        }
    }

    public void deleteServerByIp(String ip) throws RecordNotFoundException {

        Set<Server> server = repository.findByIp(ip);

        if (server.size() > 0) {
            Iterator c = server.iterator();
            Server newEntity = (Server) c.next();
            repository.deleteById(newEntity.getId());
        } else {
            throw new RecordNotFoundException("No server record exist for given id", ip);
        }
    }

    public Set<Server> getServersByName(String name) {
        Set<Server> serverList = repository.findByName(name);

        if (serverList.size() > 0) {
            return serverList;
        } else {
            Set<Server> s = new ArrayList<Server>().stream().collect(Collectors.toSet());
            return s;
        }
    }
}
