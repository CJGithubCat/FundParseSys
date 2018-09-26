package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dao.HoldPositionMapper;
import com.zsh.labouCapital.entity.HoldPosition;
import com.zsh.labouCapital.service.IHoldPositionService;

@Service
// 先不声明为原型类型，当有状态添加的时候再声明
// @Scope("prototype")
public class HoldPositionServiceImpl implements IHoldPositionService {
	@Autowired
	private HoldPositionMapper holdPositionMapper;
    @Override
    public List<HoldPosition> queryHoldPositionList(HoldPosition holdPositionParam) {
        return holdPositionMapper.queryHoldPositionList(holdPositionParam);
    }

    @Override
    public void insertHoldPositionInfo(HoldPosition holdPosition) {
        holdPositionMapper.insertHoldPositionInfo(holdPosition);
    }

    @Override
    public HoldPosition getTheLastHoldPosition(String fundCode) {
        return holdPositionMapper.getTheLastHoldPosition(fundCode);
    }
	
}
