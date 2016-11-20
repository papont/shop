package ru.samara.shop.model;

/**
 * @author papont
 * @date 13.11.16.
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
