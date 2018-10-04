package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Item;

public interface ClienteDAO {

   public void save(Item it); //throws PersistenceException;

   public Item load(int id); // throws PersistenceException;

}
