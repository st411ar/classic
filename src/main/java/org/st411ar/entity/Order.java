package org.st411ar.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
   	private User user;

    @OneToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn
	private Book book;
}