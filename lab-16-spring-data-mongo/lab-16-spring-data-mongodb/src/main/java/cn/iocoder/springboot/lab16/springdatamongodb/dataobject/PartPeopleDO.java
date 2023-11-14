package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/13 20:19
 */
public class PartPeopleDO {
    private String models;
    private String name;
    private String job;
    private Integer age;
    private String summary;

    public String getModel() {
        return models;
    }

    public void setModel(String models) {
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "PartPeopleDO{" +
                "models='" + models + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", age=" + age +
                ", summary='" + summary + '\'' +
                '}';
    }
}
