package com.zsh.labouCapital.pojo;

public class Cells {
		int mcc;//国家编码
		int mnc;//运营编码
		int lac;//基站位置区编码
		int cellid;//基站小区编码
		int sdb;//基站信号强度
		
		public int getSdb() {
			return sdb;
		}
		public void setSdb(int sdb) {
			this.sdb = sdb;
		}
		public int getMcc() {
			return mcc;
		}
		public void setMcc(int mcc) {
			this.mcc = mcc;
		}
		public int getMnc() {
			return mnc;
		}
		public void setMnc(int mnc) {
			this.mnc = mnc;
		}
		public int getLac() {
			return lac;
		}
		public void setLac(int lac) {
			this.lac = lac;
		}
		public int getCellid() {
			return cellid;
		}
		public void setCellid(int cellid) {
			this.cellid = cellid;
		}
}

