package com.zsh.labouCapital.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.HoldPositionMapper;
import com.zsh.labouCapital.dao.NetWorthHistoryMapper;
import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.HoldPosition;
import com.zsh.labouCapital.service.IHoldPositionService;
import com.zsh.labouCapital.service.INetWorthHistoryService;

@Service
// 先不声明为原型类型，当有状态添加的时候再声明
// @Scope("prototype")
public class HoldPositionServiceImpl extends BaseServiceImpl<HoldPosition> implements IHoldPositionService {
	@Autowired
	private HoldPositionMapper netWorthHistoryMapper;
	
}
