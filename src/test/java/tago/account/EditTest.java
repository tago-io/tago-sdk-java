package tago.account;

import domain.AccountProfileResult;
import domain.StringResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EditTest {
    
    public EditTest() {
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
    public void testEdit() {
        System.out.println("Account edit");
        Account a = new Account();
        
        a.company = "Tago Testing";
        a.name = "Java SDK test user";
        a.timezone = "America/Sao_Paulo";
        
        StringResult sr = a.edit(a);

        assertEquals(sr.status, true);
    }
}
