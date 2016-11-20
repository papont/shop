package ru.samara.shop.model;

/**
 * @author papont
 * @date 13.11.16.
 */
public class NamedEntity extends BaseEntity {

    protected String name;

    public NamedEntity() {
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
