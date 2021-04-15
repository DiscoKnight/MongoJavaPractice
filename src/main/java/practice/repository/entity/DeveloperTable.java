package practice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dev_table")
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "developername")
    private String developerName;

}
