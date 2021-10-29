package com.yolo.workdemo.domain;

import java.util.ArrayList;

//文件类-WordDocument
public class WordDocument implements Cloneable {
    //文本
    private String mText;
    //图片名列表
    private ArrayList mImages = new ArrayList();

    //原始拷贝方法 浅拷贝
    @Override
    protected WordDocument clone() {
        WordDocument document = null;
        try {
            document = (WordDocument) super.clone();
            document.mText = this.mText;
            document.mImages = this.mImages;
            return document;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    //深拷贝--建议使用
//    @Override
//    protected WordDocument clone() {
//        WordDocument document = null;
//        try {
//            document = (WordDocument) super.clone();
//            document.mText = this.mText;
//            //对yimage函数使用coloen
//            document.mImages = (ArrayList) this.mImages.clone();
//            return document;
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public WordDocument() {
        System.out.println("----------WordDocument构造函数");
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public ArrayList getmImages() {
        return mImages;
    }

    public void addImages(String img) {
        this.mImages.add(img);
    }

    public void showDocument() {
        System.out.println("word content start");
        System.out.println("Text:" + mText);
        System.out.println("imagesList:");
        for (Object imageName : mImages) {
            System.out.println("image name:" + imageName);
        }
        System.out.println("word content end");
    }
}
