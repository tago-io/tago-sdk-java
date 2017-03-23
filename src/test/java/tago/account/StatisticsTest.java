package tago.account;

import domain.AccountInfoResult;
import domain.AccountStatisticsResult;
import domain.FindDataCountResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    public StatisticsTest() {
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
    public void testStatistics() {
        System.out.println("Account statistics");
        Account a = new Account();
        AccountStatisticsResult asr = a.statistics(null);

        assertEquals(asr.status, true);
    }
}
