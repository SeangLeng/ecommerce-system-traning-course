package mptc.seangleng.ecommerce.ecommerce.entity;

import java.util.Objects;

// Abstract class cannot create object.
public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    /*
        This equals override function used to compare object, cuz in microservice id will be uses across services,
        if the there is compilation happened, it will be depended on override.
   */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
