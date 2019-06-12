package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.dao.FundSummaryMapper;
import com.zsh.labouCapital.dao.UserDao;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.service.IFundSummaryService;

@Service
// 先不声明为原型类型，当有状态添加的时候再声明
// @Scope("prototype")
public class FundSummaryServiceImpl extends BaseServiceImpl<FundSummary> implements IFundSummaryService {
	@Autowired
	private FundSummaryMapper fundSummaryMapper;

	@Override
	public FundSummary getFundSummaryByCode(String fundCode) {
		if(StringUtils.isEmpty(fundCode)){
			return null;
		}
		return fundSummaryMapper.getFundSummaryByCode(fundCode);
	}

	@Override
	public List<FundSummary> queryAllFundSummary() {
		return fundSummaryMapper.queryAllFundSummary();
	}
	
	@Override
    public List<FundSummary> queryFundSummaryByEample(FundSummary examPle) {
        return fundSummaryMapper.queryFundSummaryByEample(examPle);
    }
	
	@Override
	public List<FundSummary> queryFundSummaryNoHsitory() {
		return fundSummaryMapper.queryFundSummaryNoHsitory();
	}

}
