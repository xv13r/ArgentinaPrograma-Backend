package ar.gob.inti.argentinaprograma.miportfolio.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import ar.gob.inti.argentinaprograma.miportfolio.annotations.IdValidation;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @IdValidation
    private UUID id;
    
    @Column(nullable = false, length = 64, name = "name")
    @NotBlank(message = "Nombre requerido")
    private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role (String name){
        this.name  = name;
    }

    public Role(UUID id) {
		super();
		this.id = id;
	}

    public UUID getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	@Override
	public String toString() {
		return this.name;
	}

    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}

    public void removeUser(User user) {
        this.users.remove(user);
    }  
}