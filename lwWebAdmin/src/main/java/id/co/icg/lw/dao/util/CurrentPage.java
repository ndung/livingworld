/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.dao.util;

import java.util.List;

/**
 *
 * @author Hatta Palino
 */
public class CurrentPage {  
    private int  pageNumber;  
    private int  pagesAvailable;  
    private int  pageSize;
    private int  totalSize;
    private List pageItems;
    
    public void setPageNumber(int pageNumber) {  
        this.pageNumber = pageNumber;  
    }  
    public void setPagesAvailable(int pagesAvailable) {  
        this.pagesAvailable = pagesAvailable;  
    }  
    public void setPageItems(List pageItems) {  
        this.pageItems = pageItems;  
    }  
    public int getPageNumber() {  
        return pageNumber;  
    }  
    public int getPagesAvailable() {  
        return pagesAvailable;  
    }  
    public List getPageItems() {  
        return pageItems;  
    }  
    public int getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
}  