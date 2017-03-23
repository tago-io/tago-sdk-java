package tago.account;

import domain.AccountProfileResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListProfileTest {
    
    public ListProfileTest() {
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
    public void testProfileList() {
        System.out.println("Account profile list");
        Account a = new Account();
        
        AccountProfileResult apr = a.profileList();

        assertEquals(apr.status, true);
    }
}
