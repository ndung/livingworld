/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.mkiosksaldo.model.report;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hatta Palino
 * @param <T>
 */
public class SheetExcel<T> implements Serializable {
    
    private String             nameSheet;
    private Object[]           summarys;
    private List<T> reportTrxs;

    public String getNameSheet() {
        return nameSheet;
    }

    public void setNameSheet(String nameSheet) {
        this.nameSheet = nameSheet;
    }

    public List<T> getReportTrxs() {
        return reportTrxs;
    }

    public void setReportTrxs(List<T> reportTrxs) {
        this.reportTrxs = reportTrxs;
    }

    public void setSummarys(Object[] summarys) {
        this.summarys = summarys;
    }

    public Object[] getSummarys() {
        return summarys;
    }

    
}
