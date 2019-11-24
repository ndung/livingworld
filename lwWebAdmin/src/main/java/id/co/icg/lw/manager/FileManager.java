/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.manager;

import net.sourceforge.stripes.action.FileBean;

import java.nio.file.Path;


/**
 *
 * @author Fauzi Marjalih
 */
public interface FileManager {
    public String saveFile(FileBean fileBean);
}
