package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("idit")int id);
    
    public void insertarItem(@Param("item")Item it);

	public void actualizarTarifaItem(@Param("idit")int id,@Param("tarifait")long tarifa);

	public Date consultarFechafinReta(@Param("idit")int id);
     
}
