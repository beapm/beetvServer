/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.beetvServer.api;

import javax.servlet.http.HttpSession;
import net.ausiasmarch.beetvServer.repository.ActuacionRepository;
import net.ausiasmarch.beetvServer.service.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author beapm
 */

@RestController
@RequestMapping("/actuacion")
public class ActuacionController {
    
    @Autowired
    HttpSession oHttpSession;

    @Autowired
    ActuacionRepository oActuacionRepository;
        
    @Autowired
    FillService oFillService;
    
}
