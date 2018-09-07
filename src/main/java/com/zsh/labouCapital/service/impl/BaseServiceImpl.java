package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.BaseSqlMapper;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.BaseService;


public abstract class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	private BaseSqlMapper<T> sqlMapper;

	@Transactional
	public int add(T entity) throws Exception {
		return sqlMapper.add(entity);
	}

	@Transactional
	public ReturnValue edit(T entity) throws Exception {
		int editCount = sqlMapper.edit(entity);
		ReturnValue rv = new ReturnValue();
		if (editCount > 0) {
			rv.setSuccess(true);
		} else {
			rv.editError();
		}
		return rv;
	}

	@Transactional
	public int removeById(String id) throws Exception {
		return sqlMapper.removeById(id);
	}

	public T find(T entity) throws Exception {
		return this.sqlMapper.find(entity);
	}

	public T findById(String id) throws Exception {
		return this.sqlMapper.findById(id);
	}

	public int countAll(T entity) throws Exception {
		return sqlMapper.countAll(entity);
	}

	public List<T> findByPage(T entity, RowBounds rowBounds) throws Exception {
		return sqlMapper.findByPage(entity, rowBounds);
	}

	public List<T> findList(T entity) throws Exception {
		return sqlMapper.findList(entity);
	}

}
