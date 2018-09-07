package com.zsh.labouCapital.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.ExamSummary;
import com.zsh.labouCapital.entity.ExamSummaryVo;
import com.zsh.labouCapital.entity.StationExamNoticeResult;

public interface ExamSummaryMapper extends BaseSqlMapper<ExamSummary> {
	void addExamSummary(ExamSummary examSummary);

	int countExamSummaryByDate(String summaryYearMonth);

	int getExamSummarysCount(ExamSummaryVo examSummaryVo);

	List<ExamSummary> queryExamSummarysPage(ExamSummaryVo examSummaryVo, RowBounds rowBounds);

	List<ExamSummary> queryAllExamSummaryByDateAndName(Map<String, Object> params);

	void genderDepartExamNotice(DepartmentExamNoticeResult departExamNotice);

	void genderStationExamNotice(StationExamNoticeResult stationExamNotice);

	void updateExamSummayStatus(Map<String, Object> param);

	void genderStationNotice(StationExamNoticeResult stationExamNotice);

	StationExamNoticeResult checkIsJyzNoticeExist(ExamSummary examSummary);

	DepartmentExamNoticeResult checkIsBmNoticeExist(ExamSummary examSummary);

	List exportExamSummary(Map<String, Object> params);

}
