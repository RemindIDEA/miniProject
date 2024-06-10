package com.mini.test.Service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MapServiceImpl implements MapService {
    //요청 조건에 맞는 파일들 백업폴더에 옮겨주기
    @Override
    public void moveBackup(int xmin, int xmax, int ymin, int ymax) {
        //기존 파일 저장된 폴더 경로
        String basePath = "~/Zomboid/Saves/Multiplayer/dotoritest_Season2_Server_001";
        //기존 폴더가 존재하는지 확인
        if(Files.notExists(Paths.get(basePath))){
            return;
        }
        //백업폴더 만들 경로(범위 맞춰서 생성 필요)
        String backupPath = basePath + "/backup/"+xmin+"_"+xmax+"_"+ymin+"_"+ymax;
        try {
            //폴더 생성.
            Files.createDirectories(Paths.get(backupPath));
            //기본폴더에 파일 파싱 후 범위에 적합한지 확인 후 가져오기
            List<Path> filesToMove = getFilesRange(basePath, xmin, xmax, ymin, ymax);
            for (Path file : filesToMove) {
                Path target = Paths.get(backupPath, file.getFileName().toString());
                System.out.println("target ============== " + target);
                //타겟된 파일 확인후 이동시키기
                Files.move(file, target);
            }
            System.out.println("success!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //필터로 조건에 맞는 파일만 골라내기
    @Override
    public List<Path> getFilesRange(String basePath, int xmin, int xmax, int ymin, int ymax) throws IOException {
        try (Stream<Path> files = Files.list(Paths.get(basePath))) {
            //stream 으로 한번에 처리(필터로 범위에 안드는건 리스트에 추가x)
            return files
                    .filter(Files::isRegularFile)
                    .filter(file -> isRange(file, xmin, xmax, ymin, ymax))
                    .collect(Collectors.toList());
        }
    }
    //요청 범위에 맞는지 체크
    @Override
    public boolean isRange(Path file, int xmin, int xmax, int ymin, int ymax) {
        //경로를 통해 파일이름 알아오기
        String fileName = file.getFileName().toString();
        //파일 이름이 map 으로 시작하고 파일 확장자가 .txt 인경우만 통과
        if (fileName.startsWith("map") && fileName.endsWith(".bin")) {
            //subString을 사용하여 숫자만 분리 (숫자 시작 인덱스 4, 마지막 4는 확장자) 후에 split 으로 숫자만 뽑아내기
            String[] parts = fileName.substring(4, fileName.length() - 4).split("_");
            //숫자 범위가 0~9까지 포함이므로 x,y min 과 max 의 범위 조절 후 비교하기.
            int x = Integer.parseInt(parts[0])*10;
            int y = Integer.parseInt(parts[1])*10;
            //구한 x,y값의 범위에서 최소값이 max보다 작으면 true
            //해당 범위 안에 들어야 true 반환
            return x >= xmin-(xmin%10) && x <= xmax  && y >= ymin-(ymin%10) && y <= ymax;
        }
        return false;
    }
}
