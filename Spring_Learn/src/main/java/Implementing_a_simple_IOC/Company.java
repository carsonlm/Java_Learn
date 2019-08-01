package Implementing_a_simple_IOC;

/**
 * a bean demo
 */
public class Company {

    private String name;
    private String type;
    private Number peopleNum;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Number getPeopleNum() {
        return peopleNum;
    }
    public void setPeopleNum(Number peopleNum) {
        this.peopleNum = peopleNum;
    }
}
