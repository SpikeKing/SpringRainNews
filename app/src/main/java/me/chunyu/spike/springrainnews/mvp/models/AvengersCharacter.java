package me.chunyu.spike.springrainnews.mvp.models;

/**
 * 复仇者成员类
 * <p>
 * Created by wangchenlong on 16/1/21.
 */
public class AvengersCharacter {

    private int id;
    private String name;
    private String description;
    private ThumbnailEntity thumbnail;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(ThumbnailEntity thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ThumbnailEntity getThumbnail() {
        return thumbnail;
    }

    public static class ThumbnailEntity {
        private String path;
        private String extension;

        public void setPath(String path) {
            this.path = path;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }
    }
}


