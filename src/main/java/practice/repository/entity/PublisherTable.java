package practice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "publisher_table")
@AllArgsConstructor
@NoArgsConstructor
public class PublisherTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "publisher_name")
    private String publisherName;


}
