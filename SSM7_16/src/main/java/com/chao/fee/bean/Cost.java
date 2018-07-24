package com.chao.fee.bean;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class Cost {
    private String cost_id,base_duration,base_cost,unit_cost;
    private String name,status,descr,creatime,startime,cost_type;

    @Override
    public String toString() {
        return "Cost{" +
                "cost_id='" + cost_id + '\'' +
                ", base_duration='" + base_duration + '\'' +
                ", base_cost='" + base_cost + '\'' +
                ", unit_cost='" + unit_cost + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", descr='" + descr + '\'' +
                ", creatime='" + creatime + '\'' +
                ", startime='" + startime + '\'' +
                ", cost_type='" + cost_type + '\'' +
                '}';
    }

    public String getCost_id() {
        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
    }

    public String getBase_duration() {
        return base_duration;
    }

    public void setBase_duration(String base_duration) {
        this.base_duration = base_duration;
    }

    public String getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(String base_cost) {
        this.base_cost = base_cost;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }
}
