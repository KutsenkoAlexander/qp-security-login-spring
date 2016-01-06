package ua.vza.vde.qp.obj;

/**
 * Created by velenteenko on 25.06.15.
 */
public class User {

    private Long UserId;
    private String fio;

    public User() {}

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }

    @Override
    public String toString() {
        return new StringBuffer("fio: ").append(this.fio).append(" id: ").append(this.UserId).toString();
    }
}
