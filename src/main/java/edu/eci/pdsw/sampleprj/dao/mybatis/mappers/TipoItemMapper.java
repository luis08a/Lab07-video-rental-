package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface TipoItemMapper {
	public void insertarTipoItem(TipoItem ti);
	public TipoItem consultarTipoItem(@Param("id")int id);
	public List<TipoItem> consultarTodosLosTiposItem();

}
