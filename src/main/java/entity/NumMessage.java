package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nums", schema = "public")
public class NumMessage {

    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "num")
    private int num;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumMessage that = (NumMessage) o;

        if (getId() != that.getId()) return false;
        return getNum() == that.getNum();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getNum();
        return result;
    }
}
