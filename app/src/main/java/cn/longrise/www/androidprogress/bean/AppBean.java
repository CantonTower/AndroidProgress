package cn.longrise.www.androidprogress.bean;

/**
 * Created by Administrator on 2018/5/6.
 */

public class AppBean {
    private String name;
    private int thumId;
    private String version;
    private int fileSize;

    public AppBean(String name, int thumId, String version, int fileSize) {
        this.name = name;
        this.thumId = thumId;
        this.version = version;
        this.fileSize = fileSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumId() {
        return thumId;
    }

    public void setThumId(int thumId) {
        this.thumId = thumId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "name='" + name + '\'' +
                ", thumId=" + thumId +
                ", version='" + version + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
