package com.example.photoapplicationexample;

public class photos {

    String path;
    String FolderName;
    int numberOfPics=0;
    String firstPic;

    public photos(String path, String folderName, int numberOfPics, String firstPic) {
        this.path = path;
        FolderName = folderName;
        this.numberOfPics = numberOfPics;
        this.firstPic = firstPic;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }



    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public photos(String path) {
        this.path = path;
    }

    public void addpics(){
        this.numberOfPics++;
    }


}

