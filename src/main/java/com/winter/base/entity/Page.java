/**
 * 
 */
package com.winter.base.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 下午5:29:17
 */
public class Page<T> {

	private List<T> list;
	private int total;//
	private int totalpage;
	private int pagesize;
	private int start;
	private int curpage;
	private int startpage;
	private int endpage;
	private int prepage;
	private int nextpage;

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
		if (this.pagesize <= 0)
			this.pagesize = 10;
		if ((this.total % this.pagesize) == 0) {
			this.totalpage = this.total / this.pagesize;
		} else {
			this.totalpage = (this.total / this.pagesize) + 1;
		}
		if ((this.start % this.pagesize) == 0) {
			this.curpage = this.start / this.pagesize;
		} else {
			this.curpage = (this.start / this.pagesize) + 1;
		}
		this.startpage = 1;
		this.endpage = totalpage;
		if (this.curpage <= 1 && totalpage > 1) {
			this.prepage = 1;
			this.nextpage = this.curpage + 1;
		} else if (this.curpage <= 1 && totalpage <= 1) {
			this.prepage = 1;
			this.nextpage = 1;
		} else if (this.curpage >= this.totalpage) {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage;
		} else {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage + 1;
		}
	}

	public int nextPage() {
		if (curpage >= totalpage) {
			this.curpage = totalpage;
		} else {
			this.curpage = this.curpage + 1;
		}
		this.start = this.curpage * pagesize;
		if (this.curpage <= 1 && totalpage > 1) {
			this.prepage = 1;
			this.nextpage = this.curpage + 1;
		} else if (this.curpage <= 1 && totalpage <= 1) {
			this.prepage = 1;
			this.nextpage = 1;
		} else if (this.curpage >= this.totalpage) {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage;
		} else {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage + 1;
		}
		return this.curpage;
	}

	public int prePage() {
		if (curpage == 1) {
			this.curpage = 1;
		} else {
			this.curpage = this.curpage - 1;
		}
		this.start = this.curpage * pagesize;
		if (this.curpage <= 1 && totalpage > 1) {
			this.prepage = 1;
			this.nextpage = this.curpage + 1;
		} else if (this.curpage <= 1 && totalpage <= 1) {
			this.prepage = 1;
			this.nextpage = 1;
		} else if (this.curpage >= this.totalpage) {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage;
		} else {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage + 1;
		}
		return this.curpage;
	}

	public Page(int total, int curpage) {
		this.total = total;
		this.curpage = curpage;
		this.pagesize = 10;
		this.start = (this.curpage - 1) * this.pagesize;
		this.startpage = 1;
		this.endpage = this.total / this.pagesize;
		if ((this.total % this.pagesize) == 0) {
			this.totalpage = this.total / this.pagesize;
		} else {
			this.totalpage = (this.total / this.pagesize) + 1;
		}
		this.endpage = totalpage;
		if (this.curpage <= 1 && totalpage > 1) {
			this.prepage = 1;
			this.nextpage = this.curpage + 1;
		} else if (this.curpage <= 1 && totalpage <= 1) {
			this.prepage = 1;
			this.nextpage = 1;
		} else if (this.curpage >= this.totalpage) {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage;
		} else {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage + 1;
		}
		list = new ArrayList<T>();
	}

	public Page(int total, int curpage, int pagesize) {
		this.total = total;
		this.curpage = curpage;
		this.pagesize = pagesize;
		if (this.pagesize <= 0)
			this.pagesize = 10;
		this.start = (this.curpage - 1) * this.pagesize;
		this.startpage = 1;
		this.endpage = this.total / this.pagesize;
		if ((this.total % this.pagesize) == 0) {
			this.totalpage = this.total / this.pagesize;
		} else {
			this.totalpage = (this.total / this.pagesize) + 1;
		}
		this.endpage = totalpage;
		if (this.curpage <= 1 && totalpage > 1) {
			this.prepage = 1;
			this.nextpage = this.curpage + 1;
		} else if (this.curpage <= 1 && totalpage <= 1) {
			this.prepage = 1;
			this.nextpage = 1;
		} else if (this.curpage >= this.totalpage) {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage;
		} else {
			this.prepage = this.curpage - 1;
			this.nextpage = this.curpage + 1;
		}
		list = new ArrayList<T>();
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getPrepage() {
		return prepage;
	}

	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	@Override
	public String toString() {
		return "Page [list=" + list + ", total=" + total + ", totalpage=" + totalpage + ", pagesize=" + pagesize
				+ ", start=" + start + ", curpage=" + curpage + ", startpage=" + startpage + ", endpage=" + endpage
				+ ", prepage=" + prepage + ", nextpage=" + nextpage + "]";
	}

}
