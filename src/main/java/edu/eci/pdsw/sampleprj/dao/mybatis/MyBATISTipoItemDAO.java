package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{
	@Inject
	private TipoItemMapper tiMapper;    
	@Override
	public void save(TipoItem ti) throws PersistenceException {
		try{
			tiMapper.insertarTipoItem(ti);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el item "+ti.toString(),e);
		}  
	}

	@Override
	public TipoItem load(int id) throws PersistenceException {
		try{
			return tiMapper.consultarTipoItem(id);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item "+id,e);
		}
	}

}
