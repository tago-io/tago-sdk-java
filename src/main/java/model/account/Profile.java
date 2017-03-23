package model.account;

import java.util.Date;
import java.util.List;

public class Profile {
    public Account account;
    public Account ref_account;
    public List<String> management;
    public String id;
    public Boolean main;
    public Date created_at;
    public Date updated_at;
}
