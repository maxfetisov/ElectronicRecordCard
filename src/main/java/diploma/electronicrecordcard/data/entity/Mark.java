package diploma.electronicrecordcard.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "mark")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Mark {

    @Id
    @Column(name = "id")
    Short id;

    @Column(name = "name")
    String name;

    @Column(name = "title")
    String title;

    @Column(name = "value")
    Short value;

    @Column(name = "version")
    Long version;

    @OneToMany(mappedBy = "mark")
    List<StudentMark> studentMarks;

    @ManyToMany
    @JoinTable(
            name = "mark_control_type",
            joinColumns = @JoinColumn(name = "mark_id"),
            inverseJoinColumns =  @JoinColumn( name = "control_type_id")
    )
    List<ControlType> controlTypes;

}
