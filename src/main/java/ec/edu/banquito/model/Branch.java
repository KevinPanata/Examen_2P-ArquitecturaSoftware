package ec.edu.banquito.model;

import ec.edu.banquito.model.annotation.Table;
import ec.edu.banquito.model.annotation.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@Table(name = "branch")
@Document(collection = "branch")
public class Branch {

    @Id
    @Column(name = "id")
    @Field("id")
    private String id;

    @Column(name = "emailAddress")
    @Field("emailAddress")
    private String emailAddress;

    @Column(name = "name")
    @Field("name")
    private String name;

    @Column(name = "phoneNumber")
    @Field("phoneNumber")
    private String phoneNumber;

    @Column(name = "state")
    @Field("state")
    private BranchState state;

    @Column(name = "creationDate")
    @Field("creationDate")
    private OffsetDateTime creationDate;

    @Column(name = "lastModifiedDate")
    @Field("lastModifiedDate")
    private OffsetDateTime lastModifiedDate;

    public Branch() {
    }

    public Branch(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state=" + state +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}