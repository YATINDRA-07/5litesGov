package com.yatindra.a5litesgov.model;

public class PingNode {
    private String macid;
    private int no_pings;
    private String timestamp;
    private String phn_no;
    public PingNode(String macid, int no_pings, String timestamp, String phn_no) {
        this.macid = macid;
        this.no_pings = no_pings;
        this.timestamp = timestamp;
        this.phn_no = phn_no;
    }

    public String getMacid() {
        return macid;
    }

    public void setMacid(String macid) {
        this.macid = macid;
    }

    public String getPhn_no() {
        return phn_no;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }

    public int getNo_pings() {
        return no_pings;
    }

    public void setNo_pings(int no_pings) {
        this.no_pings = no_pings;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
