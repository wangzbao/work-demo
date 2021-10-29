package com.yolo.workdemo.domain;

//输出类-client
public class Client {
    public static void main(String[] args) {
        //1构建文档对象
        WordDocument wordDocument = new WordDocument();
        //2编辑文档添加图片等
        wordDocument.setmText("这是一篇文档");
        wordDocument.addImages("图片一");
        wordDocument.addImages("图片二");
        wordDocument.addImages("图片三");
        wordDocument.showDocument();
        //以原始文档为原型,拷贝一份副本
        WordDocument doc = wordDocument.clone();
        doc.showDocument();
        //修改副本不会影响原始文档
        doc.setmText("修改doc文本");
        doc.addImages("哈哈.jpg");
        doc.showDocument();
        wordDocument.showDocument();
    }
}
