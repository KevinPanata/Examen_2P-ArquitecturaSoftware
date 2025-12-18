package ec.edu.banquito.model;

import ec.edu.banquito.model.annotation.Table;
import ec.edu.banquito.model.annotation.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Table(name = "branchHoliday")
@Document(collection = "branchHoliday")
public class BranchHoliday {

    @Id
    @Column(name = "id")
    @Field("id")
    private String id;

    @Column(name = "branchId")
    @Field("branchId")
    private String branchId;

    @Column(name = "date")
    @Field("date")
    private LocalDate date;

    @Column(name = "name")
    @Field("name")
    private String name;

    public BranchHoliday() {
    }

    public BranchHoliday(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchHoliday)) return false;
        BranchHoliday that = (BranchHoliday) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BranchHoliday{" +
                "id='" + id + '\'' +
                ", branchId='" + branchId + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}