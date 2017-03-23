package tago.account;

import domain.AccountInfoResult;
import domain.FindDataCountResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InfoTest {
    
    public InfoTest() {
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
    public void testInfo() {
        System.out.println("Account Info");
        Account a = new Account();
        AccountInfoResult ac = a.info();

        assertEquals(ac.status, true);
    }
}
