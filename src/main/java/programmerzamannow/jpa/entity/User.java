package programmerzamannow.jpa.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity()
@Table(name = "users")
public class User {
    @Id
    private String id;

    private String name;

    private String getId() {
        return id;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(
        name = "id",
        referencedColumnName = "id"
    )
    public Credential credential;

    @OneToOne(mappedBy = "user")
    public Wallet wallet;

    @ManyToMany
    @JoinTable(
        name = "users_like_products",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id")
    )
    private Set<Product> likes;

    public Set<Product> getLikes() {
        return likes;
    }

    public void setLikes(Set<Product> likes) {
        this.likes = likes;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @OneToOne(mappedBy = "id")
    // @PrimaryKeyJoinColumn
    // @JoinColumn(name = "id")
    // private Credential credential;
}
