package spring.core.data.vo;

import org.apache.ibatis.annotations.Param;

public class EventVO {
    Long pk;
    String name;
    Long basePricePk;
    Long ratingPk;

    public Long getPk() {
        return pk;
    }

    public void setPk(final Long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getBasePricePk() {
        return basePricePk;
    }

    public void setBasePricePk(final Long basePricePk) {
        this.basePricePk = basePricePk;
    }

    public Long getRatingPk() {
        return ratingPk;
    }

    public void setRatingPk(final Long ratingPk) {
        this.ratingPk = ratingPk;
    }
}
