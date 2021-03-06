package org.st411ar.papers.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Column(name="name")
	private String name;
}