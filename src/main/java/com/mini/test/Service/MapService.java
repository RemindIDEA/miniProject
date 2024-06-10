package com.mini.test.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface MapService {
    public void moveBackup(int xmin, int xmax, int ymin, int ymax);
    public List<Path> getFilesRange(String basePath, int xmin, int xmax, int ymin, int ymax) throws IOException;
    public boolean isRange(Path file, int xmin, int xmax, int ymin, int ymax);
}
