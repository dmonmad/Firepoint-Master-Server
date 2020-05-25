/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.controllers;

/**
 *
 * @author Nightm4re
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.nightm4re.resfulserver.model.Server;
import com.nightm4re.resfulserver.exceptions.RecordNotFoundException;
import com.nightm4re.resfulserver.services.ServerService;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
@RestController
@RequestMapping("/server")
public class ServerServiceController
{
    @Autowired
    ServerService service;
 
    @GetMapping
    public ResponseEntity<Set<Server>> getAllServers() {
        Set<Server> list = service.getAllServers();
 
        return new ResponseEntity<Set<Server>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{ip}")
    public ResponseEntity<Server> getServerByIp(@PathVariable("ip") String ip)
                                                    throws RecordNotFoundException {
    	Server entity = service.getServerByIp(ip);
 
        return new ResponseEntity<Server>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{name}")
    public ResponseEntity<Set<Server>> getServersByName(@PathVariable("name") String name) {
    	Set<Server> list = service.getServersByName(name);
 
        return new ResponseEntity<Set<Server>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping
    public ResponseEntity<Server> createServer(@Valid @RequestBody Server myServer, HttpServletRequest request){
        System.out.println(myServer.toString());
        String ipAddress = request.getRemoteAddr();
        myServer.setIp(ipAddress);
    	Server created = service.createServer(myServer);
        System.out.println(created.toString());
        return new ResponseEntity<Server>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Server> UpdateServer(@Valid @RequestBody Server myServer)
                                                    throws RecordNotFoundException {
    	Server updated = service.updateServer(myServer);
        return new ResponseEntity<Server>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{ip}")
    public HttpStatus deleteServerByIp(@PathVariable("ip") String ip)
                                                    throws RecordNotFoundException {
        service.deleteServerByIp(ip);
        return HttpStatus.ACCEPTED;
    }
 
}
