package tago;

import domain.AccountProfileResult;
import domain.StringResult;
import model.account.Account;
import model.account.Profile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountDeleteProfileTest {
    
    public AccountDeleteProfileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testProfileDelete() {
        System.out.println("Account profile list");
        Account a = new Account();
        a.profileCreate("javasdkprofile@test.com");
        AccountProfileResult apr = a.profileList();
        String id = null;
        for (Profile p: apr.result) {
            if(p.ref_account.email.equals("javasdkprofile@test.com")){
                id = p.id;
                break;
            }
        }
        StringResult dsr = a.profileDelete(id);
        
        assertEquals(dsr.status, true);
    }
}
