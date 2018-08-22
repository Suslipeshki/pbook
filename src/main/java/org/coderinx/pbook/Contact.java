package org.coderinx.pbook;

import java.sql.Date;

public class Contact {

    private Integer id;
    private String name;
    private Date bday;
    private long pnum;

    public Contact() {
    }

    public Contact(Integer id, String name, Date bday, long pnum) {
        this.id = id;
        this.name = name;
        this.bday = bday;
        this.pnum = pnum;
    }

    public Contact(String name, Date bday, long pnum) {
        this.name = name;
        this.bday = bday;
        this.pnum = pnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public void setPnum(long pnum) {
        this.pnum = pnum;
    }

    public String getName() {
        return name;
    }

    public long getPnum() {
        return pnum;
    }
}
