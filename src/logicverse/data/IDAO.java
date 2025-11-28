/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.data;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author zohaib
 */
public interface IDAO {
    
    public boolean save(Hashtable<String,String> data);    
    public boolean delete(Hashtable<String,String> params);
    public Hashtable<String,String> load(Hashtable<String,String> params);
    public ArrayList<Hashtable<String,String>> loadAll(Hashtable<String, String> params);
    
}
