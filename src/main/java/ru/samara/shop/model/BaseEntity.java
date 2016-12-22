package ru.samara.shop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ru.samara.shop.LoggerWrapper;


import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@MappedSuperclass
@Access(AccessType.FIELD)
//@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class BaseEntity  {
    protected static final LoggerWrapper LOG = LoggerWrapper.get(BaseEntity.class);

    public static final int START_SEQ = 100000;


    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        if (id == null || that.id == null) {
            throw LOG.getIllegalStateException("Equals '" + this + "' and '" + that + "' with null id");
        }
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id;
    }

    public void update(int id) {
        if (isNew()) {
            setId(id);
        } else if (id != getId()) {
            throw LOG.getIllegalStateException(this + " has id diferent");
        }
    }
}
