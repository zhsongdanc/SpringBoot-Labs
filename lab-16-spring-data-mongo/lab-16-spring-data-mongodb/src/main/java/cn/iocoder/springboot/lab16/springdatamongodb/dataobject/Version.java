package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/13 20:33
 */
public class Version {
    private String summary;

    public Version(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Version{" +
                "summary='" + summary + '\'' +
                '}';
    }
}
