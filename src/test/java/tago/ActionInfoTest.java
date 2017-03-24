package tago;

import domain.ActionCreateResult;
import domain.ActionInfoResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionInfoTest {
    
    public ActionInfoTest() {
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
    public void test() {
        System.out.println("Action info");
        Account account = new Account();
        account.action.name = "Created by JAVA SDK";
        account.action.create();
        ActionInfoResult res = account.action.info();
        account.action.delete();
        
        assertEquals(res.status, true);        
    }
}
