package cn.jeeweb.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * 页面展示分页
 * 
 * @author 王存见
 *
 * @param <T>
 */
public class ActionResultModel<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long total; // 总数
	private List<T> rows; // 结果

	public ActionResultModel() {

	}
	
	public ActionResultModel(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}


	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}


}
