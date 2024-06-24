package com.mini.zomboid.domain;

public class BoundingBoxDTO {
    private int xmin;
    private int xmax;
    private int ymin;
    private int ymax;

    public BoundingBoxDTO(){
        this.xmin = 13579;
        this.xmax = 13590;
        this.ymin = 987;
        this.ymax = 1000;
    }
    public BoundingBoxDTO(int xmin, int xmax, int ymin, int ymax){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public int getXmin() {
        return xmin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getYmin() {
        return ymin;
    }

    public int getYmax() {
        return ymax;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }
}
