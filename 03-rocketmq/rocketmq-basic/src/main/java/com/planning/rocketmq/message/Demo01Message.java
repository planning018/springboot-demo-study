package com.planning.rocketmq.message;

/**
 * @author planning
 * @date 2020/5/26 7:38
 */
public class Demo01Message {

    public static final String TOPIC = "DEMO_01";

    /**
     * 编号
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo01Message {" +
                "id = " + id +
                "}";
    }
}
