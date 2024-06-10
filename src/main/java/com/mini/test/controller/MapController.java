package com.mini.test.controller;

import com.mini.test.Service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/backup")
    public void backupMapFIles(@RequestParam int xmin,@RequestParam int xmax,
                               @RequestParam int ymin,@RequestParam int ymax ){
        //요청받은 범위로 파일 이동시키기
        mapService.moveBackup(xmin, xmax, ymin, ymax);
    }
}
