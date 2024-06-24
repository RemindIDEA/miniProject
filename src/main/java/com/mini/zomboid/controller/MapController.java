package com.mini.zomboid.controller;

import com.mini.zomboid.Service.MapServiceImpl;
import com.mini.zomboid.domain.BoundingBoxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @Autowired
    private MapServiceImpl mapService;

    @PostMapping("/backup")
    public void backupMapFIles(@RequestParam BoundingBoxDTO boundingBoxDTO ){
        //요청받은 범위로 파일 이동시키기
        mapService.moveBackup(boundingBoxDTO);
    }
}
