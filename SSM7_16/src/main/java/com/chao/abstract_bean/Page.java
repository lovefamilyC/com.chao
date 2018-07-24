package com.chao.abstract_bean;

import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class Page<T> {
    private int allCount; //总数量
    private int onePageNum; //一页的数量
    private int currentPage; //当前页码
    private int allPage; //总页数
    private int beginNum; //起始位置
    List<T> list;           //存放account
    List<String> stringList; //存放页数

    public Page() {
    }

    public Page(int allCount, int onePageNum, int currentPage, int allPage, int beginNum, List<T> list, List<String> stringList) {
        this.allCount = allCount;
        this.onePageNum = onePageNum;
        this.currentPage = currentPage;
        this.allPage = allPage;
        this.beginNum = beginNum;
        this.list = list;
        this.stringList = stringList;
    }

    @Override
    public String toString() {
        return "Page{" +
                "allCount=" + allCount +
                ", onePageNum=" + onePageNum +
                ", currentPage=" + currentPage +
                ", allPage=" + allPage +
                ", beginNum=" + beginNum +
                ", list=" + list +
                ", stringList=" + stringList +
                '}';
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getBeginNum() {
        beginNum = (currentPage-1)*onePageNum;
        return beginNum;
    }

    public void setBeginNum(int beginNum) {
        this.beginNum = beginNum;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getOnePageNum() {
        return onePageNum;
    }

    public void setOnePageNum(int onePageNum) {
        this.onePageNum = onePageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAllPage() {
        if (allCount%onePageNum==0){
            if (allCount>0){
                allPage = allCount/onePageNum;
            }else {
                allPage = 1;
            }
        }else {
            allPage = allCount/onePageNum+1;
        }
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }
}
