package com.zscat.marketing.model;

public class IncomeData{

    public IncomeData(){}

    public IncomeData(Long fromUid, String fromIfCode, Long income1, Long income2, Long income3, Long income4) {
        this.fromUid = fromUid;
        this.fromIfCode = fromIfCode;
        this.income1 = income1;
        this.income2 = income2;
        this.income3 = income3;
        this.income4 = income4;
        this.totalIncome = income1 + income2 + income3 + income4;
    }

    private Long fromUid;
    private String fromIfCode;
    private Long income1; //新增来源收入
    private Long income2; //活跃来源收入
    private Long income3; //新增分成来源收入
    private Long income4; //活跃分成来源收入
    private Long totalIncome; //总收入

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    public String getFromIfCode() {
        return fromIfCode;
    }

    public void setFromIfCode(String fromIfCode) {
        this.fromIfCode = fromIfCode;
    }

    public Long getIncome1() {
        return income1;
    }

    public void setIncome1(Long income1) {
        this.income1 = income1;
    }

    public Long getIncome2() {
        return income2;
    }

    public void setIncome2(Long income2) {
        this.income2 = income2;
    }

    public Long getIncome3() {
        return income3;
    }

    public void setIncome3(Long income3) {
        this.income3 = income3;
    }

    public Long getIncome4() {
        return income4;
    }

    public void setIncome4(Long income4) {
        this.income4 = income4;
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }
}