package model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "pan")
@Data
public class ObjectB {

	@Id
	private String pNo;
	private String address;
}
