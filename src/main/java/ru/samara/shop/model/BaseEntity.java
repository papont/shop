package ru.samara.shop.model;

/**
 * Created by admin on 13.07.16.
 */
public class BaseEntity  {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
