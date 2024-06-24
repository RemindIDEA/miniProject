package com.mini.zomboid.Service;

import com.mini.zomboid.domain.BoundingBoxDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapServiceImpl {
    //기존 파일 저장된 폴더 경로
    @Value("${base-path}") private String basePath;
    //백업 폴더 경로
    @Value("${backup-path}") private String backupPath;

    //요청 조건에 맞는 파일들 백업폴더에 옮겨주기
    public void moveBackup(BoundingBoxDTO boundingBoxDTO) {

        //기존 폴더가 존재하는지 확인
        if(Files.notExists(Paths.get(basePath))){
            return;
        }
        //백업폴더 만들 경로(범위 맞춰서 생성 필요)
        //1. UUID 이용
        //UUID uuid = UUID.randomUUID();
        //2.timeStamp 이용
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formatTime = now.format(formatter);
        //backup Folder에 timeStamp로 파일 생성
        backupPath += formatTime;
        try {
            //폴더 생성.
            Files.createDirectories(Paths.get(backupPath));
            //기본폴더에 파일 파싱 후 범위에 적합한지 확인 후 가져오기
            List<Path> filesToMove = getFilesRange(basePath, boundingBoxDTO);
            for (Path file : filesToMove) {
                Path target = Paths.get(backupPath, file.getFileName().toString());
                //타겟된 파일 확인후 이동시키기
                Files.move(file, target);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //필터로 조건에 맞는 파일만 골라내기
    private List<Path> getFilesRange(String basePath, BoundingBoxDTO boundingBoxDTO) throws IOException {
        //조건에 맞는 파일들만 넣기
        List<Path> chosenFiles = new ArrayList<>();
        //받은 최소, 최대값 임시값에 넣어서 설정하기
        int tempXmin = boundingBoxDTO.getXmin()/10;
        int tempXmax = boundingBoxDTO.getXmax()/10;
        int tempYmin = boundingBoxDTO.getYmin()/10;
        int tempYmax = boundingBoxDTO.getYmax()/10;

        //x,y min~max까지 각각 파일을 찾기.
        for(int x=tempXmin; x<=tempXmax; x++){
            for(int y = tempYmin; y<=tempYmax; y++){
                String fileName = String.format("map_%d_%d.txt", x, y);
                //주소와 파일명으로 해당 경로 체크
                Path filePath = Paths.get(basePath, fileName);

                //파일이 존재 하면서 정상적인 파일인 경우 리스트에 추가하기
                if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
                    chosenFiles.add(filePath);
                }
            }
        }
        return chosenFiles;
    }

}
