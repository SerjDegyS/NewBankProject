package Entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Entyti implements Serializable {

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
