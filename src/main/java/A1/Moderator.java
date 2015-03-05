package A1;

/**
 * Created by Meghana on 2/27/2015.
 */

//import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotNull;
import java.util.*;


public class Moderator
{
    private  long id;
    @NotNull
    private  String name;
    @NotNull
    private  String email;
    @NotNull
    private  String password;
    private  String created_at;

    public Moderator(long id, String name, String email, String password, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }
    public Moderator(Moderator mod) {
        this.id = mod.getId();
        this.name = mod.getName();
        this.email = mod.getEmail();
        this.password = mod.getPassword();
        this.created_at = mod.getCreated_at();
    }
    public Moderator() {
        this.id = -1;
        this.name = null;
        this.email = null;
        this.password = null;
        this.created_at = null;
    }
    public void setId(long mid)
    {
        this.id = mid;
    }

    public long getId() {
        return id;
    }
    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    public String getPassword() {
        return password;
    }

    public void setCreated_at(String created)
    {
        this.created_at = created;
    }
    public String getCreated_at() {
        return created_at;
    }
}
