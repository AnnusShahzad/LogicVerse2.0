/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.data;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Data Access Object (DAO) interface defining standard CRUD operations.
 * Provides a generic contract for data persistence operations across different entity types.
 * All data is passed and retrieved using Hashtable key-value pairs for flexibility.
 * @author zohaib
 */
public interface IDAO {
    
    /**
     * Saves data to the database.
     * Creates a new record or updates an existing record based on the data provided.
     * @param data Hashtable containing key-value pairs of data to be saved
     * @return true if save operation is successful, false otherwise
     */
    public boolean save(Hashtable<String,String> data);
    
    /**
     * Deletes data from the database.
     * Removes a record matching the parameters provided.
     * @param params Hashtable containing key-value pairs to identify the record to delete
     * @return true if delete operation is successful, false otherwise
     */
    public boolean delete(Hashtable<String,String> params);
    
    /**
     * Loads a single record from the database.
     * Retrieves one record matching the parameters provided.
     * @param params Hashtable containing key-value pairs to identify the record to load
     * @return Hashtable containing the loaded record's data, or null if not found
     */
    public Hashtable<String,String> load(Hashtable<String,String> params);
    
    /**
     * Loads multiple records from the database.
     * Retrieves all records matching the parameters provided.
     * @param params Hashtable containing key-value pairs to filter records
     * @return ArrayList of Hashtables, each containing a record's data
     */
    public ArrayList<Hashtable<String,String>> loadAll(Hashtable<String, String> params);
    
}
