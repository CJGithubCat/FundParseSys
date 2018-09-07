package com.zsh.labouCapital.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.ExamSummary;
import com.zsh.labouCapital.entity.ExamSummaryVo;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface IExamEummaryService extends BaseService<ExamSummary> {
	public void addExamSummary(List<ExamSummary> examSummary) throws Exception;

	/**
	 * @Title: countExamSummaryByDate @Description: 根据年月统计传入数据的数量 @param @param
	 *         summaryYearMonth @param @return 参数 @return int 返回类型 @throws
	 */
	public int countExamSummaryByDate(String summaryYearMonth);

	public List<ExamSummary> queryExamSummarysPage(ExamSummaryVo examSummaryVo, PageBean pageBean);

	public int getExamSummarysCount(ExamSummaryVo examSummaryVo);

	public Map<String, Object> checkIsCompanysExist(List<ExamSummary> datas);

	public void genderDepartExamNotice(Map<String, Object> params);

	public void genderStationExamNotice(Map<String, Object> params);

	public void genderDepartNotice(DepartmentExamNoticeResult deNoticeResult);

	public void genderStationNotice(StationExamNoticeResult stationExamNotice);

	public boolean hasExportSalarys(Integer noticeId);

	public StationExamNoticeResult checkIsJyzNoticeExist(ExamSummary examSummary);

	public DepartmentExamNoticeResult checkIsBmNoticeExist(ExamSummary examSummary);

	public void exportExamSummary(Map<String, Object> params, HttpServletResponse response) throws Exception;

}
